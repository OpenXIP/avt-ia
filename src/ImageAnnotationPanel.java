/*
Copyright (c) 2010, Siemens Corporate Research a Division of Siemens Corporation 
All rights reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.nema.dicom.wg23.ArrayOfObjectLocator;
import org.nema.dicom.wg23.ObjectLocator;
import org.nema.dicom.wg23.Uuid;

import com.pixelmed.dicom.Attribute;
import com.pixelmed.dicom.AttributeList;
import com.pixelmed.dicom.AttributeTag;
import com.pixelmed.dicom.DicomException;
import com.pixelmed.dicom.DicomInputStream;
import com.siemens.cmiv.avt.aim.CreateAIMObject;
import com.siemens.cmiv.avt.ia.AnnotationPanel;
import com.siemens.cmiv.avt.ia.IAListener;
import com.siemens.cmiv.avt.ia.IASelectionEvent;
import com.siemens.cmiv.avt.ia.NoduleEntry;
import com.siemens.cmiv.avt.qiba.QIBAImageAnnotation;

import edu.wustl.xipApplication.wg23.OutputAvailableEvent;
import edu.wustl.xipApplication.wg23.OutputAvailableListener;
import gme.cacore_cacore._3_2.edu_northwestern_radiology.Calculation;
import gme.cacore_cacore._3_2.edu_northwestern_radiology.CalculationResult;
import gme.cacore_cacore._3_2.edu_northwestern_radiology.DICOMImageReference;
import gme.cacore_cacore._3_2.edu_northwestern_radiology.Data;
import gme.cacore_cacore._3_2.edu_northwestern_radiology.GeometricShape;
import gme.cacore_cacore._3_2.edu_northwestern_radiology.Image;
import gme.cacore_cacore._3_2.edu_northwestern_radiology.ImageAnnotation;
import gme.cacore_cacore._3_2.edu_northwestern_radiology.ImageReference;
import gme.cacore_cacore._3_2.edu_northwestern_radiology.ImagingObservationCharacteristic;
import gme.cacore_cacore._3_2.edu_northwestern_radiology.Segmentation;
import gme.cacore_cacore._3_2.edu_northwestern_radiology.SpatialCoordinate;
import gme.cacore_cacore._3_2.edu_northwestern_radiology.Study;
import gme.cacore_cacore._3_2.edu_northwestern_radiology.TwoDimensionSpatialCoordinate;
import gme.cacore_cacore._3_2.edu_northwestern_radiology.Annotation.CalculationCollection;
import gme.cacore_cacore._3_2.edu_northwestern_radiology.ImageAnnotation.SegmentationCollection;

/**
 * @author Jie Zheng
 *
 */

public class ImageAnnotationPanel extends JPanel implements ActionListener, ItemListener, IAListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4680059836530067596L;

	final int WIDTH = 350;
	
	ivCanvas mivCanvas;
    AnnotationPanel annotationPanel = new AnnotationPanel(); 
    String	outDir;
    String	annotatorID;
    
    boolean bDone = false;
    boolean bHosted = false;
    
	//get screen resolution
	private int screenHeight = (int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
	private int screenWidth = (int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
	private float heightProportion = 0;
	private float widthProportion = 0;
	
	//window/level setting
	String Preset1_Name = "Liver", Preset1_Width = "80", Preset1_Center = "60";
	String Preset2_Name = "Lung", Preset2_Width = "1500", Preset2_Center = "-600";
	String Preset3_Name = "Bone", Preset3_Width = "2000", Preset3_Center = "400";
	
	//temporary buffer for the segmentation objects
	String temp_path = "C:/temp/IA";

	boolean bShowIntersection = true;
   
    public ImageAnnotationPanel() { 
		heightProportion = (float)(screenHeight / (float)1050);
		widthProportion = (float)(screenWidth / (float)1680);

		setLayout(null);
    	
		mivCanvas = new ivCanvas();
		add(mivCanvas);
		add(annotationPanel);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); 
		mivCanvas.setBounds(getWidthValue(0), getHeightValue(0), screenSize.width - getWidthValue(WIDTH), screenSize.height);
		annotationPanel.setBounds(screenSize.width - getWidthValue(WIDTH), 0, getWidthValue(WIDTH), screenSize.height);
		
		mivCanvas.initialize();
		loadLibrary();		
	    runAnnotation();
		    
//	    annotationPanel.getNewBtn().addActionListener(this);
//	    annotationPanel.getDeleteBtn().addActionListener(this);
	    annotationPanel.getClearBtn().addActionListener(this);
	    
	    annotationPanel.getSeedBtn().addActionListener(this);	    
	    annotationPanel.getSegmentBtn().addActionListener(this);
	    annotationPanel.getUndoBtn().addActionListener(this);
	    
	    annotationPanel.getFreehandBtn().addActionListener(this);
	    annotationPanel.getPunchINBtn().addActionListener(this);
	    annotationPanel.getPunchOUTBtn().addActionListener(this);

	    annotationPanel.getRECISTBtn().addActionListener(this);
	    annotationPanel.getWHOBtn().addActionListener(this);
	    annotationPanel.getVolumeBtn().addActionListener(this);

	    annotationPanel.getPanZoomBtn().addActionListener(this);
	    annotationPanel.getRotationBtn().addActionListener(this);
	    annotationPanel.getResetBtn().addActionListener(this);

	    annotationPanel.getWLBoneBtn().addActionListener(this);
	    annotationPanel.getWLLungBtn().addActionListener(this);
	    annotationPanel.getWLLiverBtn().addActionListener(this);

	    annotationPanel.getLoadBtn().addActionListener(this);
	    annotationPanel.getSaveBtn().addActionListener(this);
	    
	    annotationPanel.getLoadDataBtn().addActionListener(this);
	    
	    annotationPanel.getContourOverlay().addItemListener(this);
	    annotationPanel.getIntersectionLine().addItemListener(this);
	    
	    annotationPanel.getLayout31Btn().addActionListener(this);
	    annotationPanel.getLayout22Btn().addActionListener(this);
	    annotationPanel.getLayout11Btn().addActionListener(this);
	    
	    annotationPanel.addIAListener(this);
	    
	    Random rand = new Random();
	    annotatorID = Integer.toString(rand.nextInt());
	    
	    loadPreferences("config\\Preferences.xml");
    }
	private int getHeightValue(int height){
		return (int)(height * heightProportion);
	}
	private int getWidthValue(int width){
		return (int)(width * widthProportion);
	}
   
	public static void main(String[] args){
		JFrame frame = new JFrame();
		ImageAnnotationPanel panel = new ImageAnnotationPanel();
		frame.add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);	
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); 
		frame.setBounds(0, 0, screenSize.width, screenSize.height);

	}
   
	public void loadLibrary() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("./ivJava.ini"));
			String line = "";
			while ((line = br.readLine()) != null) {
				if (line.length() > 0) {
					int index = line.indexOf("LoadLibrary=");
					if (index >= 0) {
						String Library = line.substring(index + 12);
						Library = Library.replace(';', ',');
						System.out.println("Loading rad extensions : ");
						System.out.println(Library);
						if (!mivCanvas.loadLibraries(Library))
							System.out.println("Not all rad extensions could be loaded");
						break;
					}
				}
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}
	
	public void runAnnotation(){	   
		File ivFile = new File("IA_II.iv");
	    String filePth;
	    if(ivFile.exists()) {
	    	filePth = ivFile.getAbsolutePath();
	    } else {
	    	return;
	    }	              	          
		if (null != mivCanvas && filePth.length() != 0) {
			try {
				mivCanvas.loadGraphOpenGL(filePth);
				mivCanvas.repaint();	    
			} catch (Exception e) {
				  e.printStackTrace();
			}
		}
	}
	
	public ivCanvas getIvCanvas(){
		return mivCanvas;
	}
	   
	public AnnotationPanel getAnnotationPanel(){
		return annotationPanel;
	}
	
	public boolean loadPreferences(String xmlConfig){
		File xmlConfigFile = new File(xmlConfig);
		
		if (!xmlConfigFile.exists())
		{
			System.out.println(xmlConfigFile.getPath()+": not exist!");
			return false;
		}
		
		try {
			SAXBuilder builder = new SAXBuilder();
			Document document;
		
			document = builder.build(xmlConfigFile);
		
			Element root = document.getRootElement();
			
			Element main_item = (root.getChild("Preferences"));
			Element items = main_item.getChild("WindowLevel");
			
			int numItems = items.getChildren().size();		
			for(int i = 0; i < numItems; i++){
				Element item = ((Element)items.getChildren().get(i));			
				
				String name = item.getChild("Name").getValue(); 
				switch (i){
				case 0:
					annotationPanel.getWLBoneBtn().setText(String.format("W/L-%s", name));
					Preset1_Name = name;
					Preset1_Width = item.getChild("WindowWidth").getValue();
					Preset1_Center = item.getChild("WindowCenter").getValue();
					break;
					
				case 1:
					annotationPanel.getWLLungBtn().setText(String.format("W/L-%s", name));
					Preset2_Name = name;
					Preset2_Width = item.getChild("WindowWidth").getValue();
					Preset2_Center = item.getChild("WindowCenter").getValue();
					break;
					
				case 2:
					annotationPanel.getWLLiverBtn().setText(String.format("W/L-%s", name));
					Preset3_Name = name;
					Preset3_Width = item.getChild("WindowWidth").getValue();
					Preset3_Center = item.getChild("WindowCenter").getValue();
					break;
					
				}
			}	
			
			Element item = main_item.getChild("ContourDisplayStyle");
			String buffer = item.getChild("LineWidth").getValue();
			getIvCanvas().set("OverlayStyle_Axial.lineWidth", buffer);
			getIvCanvas().set("OverlayStyle_Sagittal.lineWidth", buffer);
			getIvCanvas().set("OverlayStyle_Coronal.lineWidth", buffer);
			
			buffer = item.getChild("LinePattern").getValue();
			getIvCanvas().set("OverlayStyle_Axial.linePattern", buffer);
			getIvCanvas().set("OverlayStyle_Sagittal.linePattern", buffer);
			getIvCanvas().set("OverlayStyle_Coronal.linePattern", buffer);
			
			item = main_item.getChild("EditDisplayStyle");
			buffer = item.getChild("LineColor").getValue();
			getIvCanvas().set("Punch_Axial.color", buffer);
			
			buffer = item.getChild("LineWidth").getValue();
			getIvCanvas().set("Punch_Axial.width", buffer);
			
			buffer = item.getChild("LinePattern").getValue();
			getIvCanvas().set("Punch_Axial.style", buffer);
			
			item = main_item.getChild("RECISTDisplayStyle");
			buffer = item.getChild("LineColor").getValue();
			getIvCanvas().set("RECIST.linecolor", buffer);
			
			buffer = item.getChild("LineWidth").getValue();
			getIvCanvas().set("RECIST.lineWidth", buffer);
			
			buffer = item.getChild("LinePattern").getValue();
			getIvCanvas().set("RECIST.linePattern", buffer);
			
			item = main_item.getChild("WHODisplayStyle");
			buffer = item.getChild("LineColor").getValue();
			getIvCanvas().set("WHO.linecolor", buffer);
			
			buffer = item.getChild("LineWidth").getValue();
			getIvCanvas().set("WHO.lineWidth", buffer);
			
			buffer = item.getChild("LinePattern").getValue();
			getIvCanvas().set("WHO.linePattern", buffer);
			
			item = main_item.getChild("TemporaryBuffer");
			buffer = item.getChild("Path").getValue();
			if (!buffer.isEmpty())
				temp_path = buffer;
			
			File tempPath = new File(temp_path);
			
			if (!tempPath.exists())
				tempPath.mkdirs();
			
		} catch (JDOMException e) {
			System.out.println("loadPreferences error");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("loadPreferences error");
			e.printStackTrace();
		}
		return true;		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		boolean resetCursor = true;
		String msg = "Ready";
		Cursor curCursor = new Cursor(Cursor.DEFAULT_CURSOR);
		
		if(e.getSource() == annotationPanel.getClearBtn()){	

			getIvCanvas().set("Matrix_Import.index", "-1");				

			clearNodule();			

			annotationPanel.updateContourStatus(false);
			annotationPanel.getSaveBtn().setEnabled(false);
			
			msg = "Clear Lesion";
		}
		
		if(e.getSource() == annotationPanel.getSeedBtn()){
			getIvCanvas().set("Mask_Switch.index", "2");
			getIvCanvas().set("Punch_Axial.on", "FALSE");
			
			getIvCanvas().set("Seed_Switch.whichChild", "-1");
			getIvCanvas().set("Seed.on", "FALSE");

			getIvCanvas().set("Seed.clear", "");
			
			String clr = annotationPanel.getCurNoduleClr();
			if (!clr.isEmpty()){
				getIvCanvas().set("OverlayClr_Axial.color", clr);
				getIvCanvas().set("OverlayClr_Sagittal.color", clr);
				getIvCanvas().set("OverlayClr_Coronal.color", clr);
				
				clr += " 0.5";
				getIvCanvas().set("color.value", clr);
			}
			
			resetPanZoom();

			getIvCanvas().set("Seed_Switch.whichChild", "0");
			getIvCanvas().set("Seed.on", "TRUE");
			
			annotationPanel.getSegmentBtn().setEnabled(true);
			curCursor = new Cursor(Cursor.CROSSHAIR_CURSOR);
			
			curCursor = loadCursor("cursor/selpoint.gif", "pan");
			msg = "Enable seed stroke";
		}
		if(e.getSource() == annotationPanel.getSegmentBtn()){
			getIvCanvas().set("Segment.update", "");
			getIvCanvas().set("Mask_Switch.index", "0");
			
			getIvCanvas().set("Seed_Switch.whichChild", "-1");
			getIvCanvas().set("Seed.on", "FALSE");
			
			getIvCanvas().set("Punch_Axial.on", "FALSE");
			
			String seedPoints = parseOpenInventorString(getIvCanvas().get("Seed_Points.string"));
			String seedReferenceUID = parseOpenInventorString(getIvCanvas().get("Seed_UID.string"));
			annotationPanel.updateItemSeed(seedPoints, seedReferenceUID);				

			msg = "Segment Lesion";
			annotationPanel.updateContourStatus(true);
			
			updateSceneGraph();
		}
		
		if(e.getSource() == annotationPanel.getFreehandBtn()){
			resetPanZoom();
			
			getIvCanvas().set("Punch_Axial.on", "TRUE");
			getIvCanvas().set("Mask_Axial.bInside", "TRUE");
			
			annotationPanel.updateContourStatus(true);
			
			curCursor = loadCursor("cursor/selfree.gif", "draw");
			msg = "Free hand Drawing";
		}
		if(e.getSource() == annotationPanel.getPunchINBtn()){
			resetPanZoom();
			
			getIvCanvas().set("Punch_Axial.on", "TRUE");
			getIvCanvas().set("Mask_Axial.bInside", "TRUE");
			
			curCursor = loadCursor("cursor/selpunchin.gif", "pen");
			msg = "Punch IN";
		}
		if(e.getSource() == annotationPanel.getPunchOUTBtn()){
			resetPanZoom();
			
			getIvCanvas().set("Punch_Axial.on", "TRUE");
			getIvCanvas().set("Mask_Axial.bInside", "FALSE");
			
			curCursor = loadCursor("cursor/selpunchout.gif", "pen");
			msg = "Punch OUT";
		}
		if(e.getSource() == annotationPanel.getUndoBtn()){
			getIvCanvas().set("Mask_Axial.undo", "");
			
			updateSceneGraph();
			msg = "Undo editing";
			resetCursor = false;
		}
				
		if(e.getSource() == annotationPanel.getRECISTBtn()){			
			resetPanZoom();
			
//			getIvCanvas().set("WHO_Switch.whichChild", "-1");
			getIvCanvas().set("Seed_Switch.whichChild", "-1");
			getIvCanvas().set("RECIST_Switch.whichChild", "0");
			
			getIvCanvas().set("Punch_Axial.on", "FALSE");
			getIvCanvas().set("Seed.on", "FALSE");
			getIvCanvas().set("WHO.on", "FALSE");

			getIvCanvas().set("RECIST.on", "TRUE");
			
			curCursor = loadCursor("cursor/selrecist.gif", "point");
			msg = "RECIST";
		}
		if(e.getSource() == annotationPanel.getWHOBtn()){			
			resetPanZoom();
			
//			getIvCanvas().set("RECIST_Switch.whichChild", "-1");
			getIvCanvas().set("Seed_Switch.whichChild", "-1");
			getIvCanvas().set("WHO_Switch.whichChild", "0");
			
			getIvCanvas().set("Punch_Axial.on", "FALSE");
			getIvCanvas().set("Seed.on", "FALSE");
			getIvCanvas().set("RECIST.on", "FALSE");
			
			getIvCanvas().set("WHO.on", "TRUE");

			curCursor = loadCursor("cursor/selwho.gif", "point");
			msg = "WHO";
		}
		if(e.getSource() == annotationPanel.getVolumeBtn()){		
			getIvCanvas().set("Vol_Metrics.update", "");

			Object[] options = {"OK"}; 
			String recist = parseOpenInventorString(getIvCanvas().get("RECIST_inMM.input0"));
			String recistPoint = parseOpenInventorString(getIvCanvas().get("RECIST_Points.string"));
			String recistUID = parseOpenInventorString(getIvCanvas().get("RECIST_UID.string"));
			String who = parseOpenInventorString(getIvCanvas().get("WHO_inMM.input0"));
			String whoPoint = parseOpenInventorString(getIvCanvas().get("WHO_Points.string"));
			String whoUID = parseOpenInventorString(getIvCanvas().get("WHO_UID.string"));
			
			String recistPoints = parseOpenInventorString(getIvCanvas().get("RECIST_Vec3f.string"));
			String whoPoints = parseOpenInventorString(getIvCanvas().get("WHO_Vec3f.string"));
			if (recist.compareToIgnoreCase("0") == 0 && who.compareToIgnoreCase("0") == 0){
		            JOptionPane.showOptionDialog(this, "Please set \"RECIST or WHO\" ",
		                    "Warning",  
		                    JOptionPane.OK_OPTION,  
		                    JOptionPane.WARNING_MESSAGE,  
		                    null,  
		                    options,  
		                    options[0]);
		            return;
				}			
			
			String volume = parseOpenInventorString(getIvCanvas().get("Metrics_Volume.string"));
			annotationPanel.updateCurrentItem(recist, recistPoint, recistUID, who, whoPoint, whoUID, volume, recistPoints, whoPoints);				

			if (!annotationPanel.validateCurrentItem())
				return;
			
			String segUID = annotationPanel.getCurNoduleUID();
			if (!segUID.isEmpty()){
				String buffer = String.format("%s\\nodule_%s.dcm", temp_path, segUID);
				getIvCanvas().set("Export.file", buffer);
				getIvCanvas().set("Export.process", "");	
			}

			msg = "Done";
			
			annotationPanel.getSaveBtn().setEnabled(true);
			annotationPanel.getSegmentBtn().setEnabled(false);
//			annotationPanel.updateButtonStatus(false);
//			annotationPanel.updateContourStatus(false);
		}
		
		if(e.getSource() == annotationPanel.getPanZoomBtn()){
			getIvCanvas().set("Examiner_Axial.mode", "PANZOOM");
			getIvCanvas().set("Examiner_Sagittal.mode", "PANZOOM");
			getIvCanvas().set("Examiner_Coronal.mode", "PANZOOM");
			getIvCanvas().set("Examiner_VR.mode", "PANZOOM");
			
			curCursor = loadCursor("cursor/selpan.gif", "pan");
			
			msg = "PanZoom";
		}
		if(e.getSource() == annotationPanel.getRotationBtn()){
			getIvCanvas().set("Examiner_Axial.mode", "ROTATE_NORMAL");
			getIvCanvas().set("Examiner_Sagittal.mode", "ROTATE_NORMAL");
			getIvCanvas().set("Examiner_Coronal.mode", "ROTATE_NORMAL");
			getIvCanvas().set("Examiner_VR.mode", "ROTATE");
			
			curCursor = loadCursor("cursor/selrotate.gif", "rotate");

			msg = "Rotate";
		}
		if(e.getSource() == annotationPanel.getResetBtn()){
			getIvCanvas().set("Examiner_Axial.viewAll", "");
			getIvCanvas().set("Examiner_Sagittal.viewAll", "");
			getIvCanvas().set("Examiner_Coronal.viewAll", "");
			getIvCanvas().set("Examiner_VR.viewAll", "");
			
			getIvCanvas().set("Dicom_Window.index", "0");
			getIvCanvas().set("Dicom_Level.index", "0");

			getIvCanvas().set("Window_Gate.trigger", "");
			getIvCanvas().set("Level_Gate.trigger", "");

			updateSceneGraph();
			msg = "Reset";
		}

		if(e.getSource() == annotationPanel.getWLBoneBtn()){
			getIvCanvas().set("Win_Level.d", Preset3_Width);
			getIvCanvas().set("Win_Level.e", Preset3_Center);
			
			getIvCanvas().set("Dicom_Window.index", "1");
			getIvCanvas().set("Dicom_Level.index", "1");
			
			getIvCanvas().set("Window_Gate.trigger", "");
			getIvCanvas().set("Level_Gate.trigger", "");

			updateSceneGraph();
			msg = "W/L - " + Preset1_Name;
		}
		if(e.getSource() == annotationPanel.getWLLungBtn()){
			getIvCanvas().set("Win_Level.d", Preset2_Width);
			getIvCanvas().set("Win_Level.e", Preset2_Center);
			
			getIvCanvas().set("Dicom_Window.index", "1");
			getIvCanvas().set("Dicom_Level.index", "1");
			
			getIvCanvas().set("Window_Gate.trigger", "");
			getIvCanvas().set("Level_Gate.trigger", "");

			updateSceneGraph();
			msg = "W/L - " + Preset2_Name;
		}
		if(e.getSource() == annotationPanel.getWLLiverBtn()){
			getIvCanvas().set("Win_Level.d", Preset1_Width);
			getIvCanvas().set("Win_Level.e", Preset1_Center);
			
			getIvCanvas().set("Dicom_Window.index", "1");
			getIvCanvas().set("Dicom_Level.index", "1");
			
			getIvCanvas().set("Window_Gate.trigger", "");
			getIvCanvas().set("Level_Gate.trigger", "");

//			String buffer = parseOpenInventorString(getIvCanvas().get("Win_Level.b"));
			updateSceneGraph();
			msg = "W/L - " + Preset3_Name;
		}
		if(e.getSource() == annotationPanel.getLayout31Btn()){
			String id = parseOpenInventorString(getIvCanvas().get("Viewport_Layout.current"));
			String size = "[ 0 0 1 1, 0 0 0 0, 0 0 0 0, 0 0 0 0 ]";
			switch(Integer.valueOf(id).intValue()){
			case 0:
				size = "[ 0.3 0 0.7 1, 0 0.67 0.3 0.33, 0 0.33 0.3 0.33, 0 0 0.3 0.33 ]";
				break;
				
			case 1:
				size = "[ 0 0.67 0.3 0.33, 0.3 0 0.7 1, 0 0.33 0.3 0.33, 0 0 0.3 0.33 ]";
				break;
				
			case 2:
				size = "[ 0 0.67 0.3 0.33, 0 0.33 0.3 0.33, 0.3 0 0.7 1, 0 0 0.3 0.33 ]";
				break;
				
			case 3:
				size = "[ 0 0.67 0.3 0.33, 0 0.33 0.3 0.33, 0 0 0.3 0.33, 0.3 0 0.7 1 ]";
				break;
				
			default:
				break;
			}
			getIvCanvas().set("Viewport_Layout.viewports", size);
			
			updateSceneGraph();
			msg = "Layout - 3x1";
		}		
		if(e.getSource() == annotationPanel.getLayout22Btn()){
			getIvCanvas().set("Viewport_Layout.viewports", "[ 0 0 0.5 0.5, 0 0.5 0.5 0.5, 0.5 0.5 0.5 0.5, 0.5 0 0.5 0.5 ]");

			updateSceneGraph();
			msg = "Layout - 2x2";
		}		
		if(e.getSource() == annotationPanel.getLayout11Btn()){
			String id = parseOpenInventorString(getIvCanvas().get("Viewport_Layout.current"));
			String size = "[ 0 0 1 1, 0 0 0 0, 0 0 0 0, 0 0 0 0 ]";
			switch(Integer.valueOf(id).intValue()){
			case 0:
				size = "[ 0 0 1 1, 0 0 0 0, 0 0 0 0, 0 0 0 0 ]";
				break;
				
			case 1:
				size = "[ 0 0 0 0, 0 0 1 1, 0 0 0 0, 0 0 0 0 ]";
				break;
				
			case 2:
				size = "[ 0 0 0 0, 0 0 0 0, 0 0 1 1, 0 0 0 0 ]";
				break;
				
			case 3:
				size = "[ 0 0 0 0, 0 0 0 0, 0 0 1 1, 0 0 0 0 ]";
				break;
				
			default:
				break;
			}
			getIvCanvas().set("Viewport_Layout.viewports", size);
			
			updateSceneGraph();
			msg = "Layout - 1x1";
		}		
		if(e.getSource() == annotationPanel.getSaveBtn()){
//			URI uri = null;
//			try {
//				uri = new URI(outDir);
//			} catch (URISyntaxException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//			File file = new File(uri);
//			File aimFile = null;
//			try {
//				aimFile = File.createTempFile("AVT-AIM", ".xml", file);
//			} catch (IOException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			};
	
			System.out.println("Begin annotation");
			
			CreateAIMObject aimCreator = new CreateAIMObject();
			aimCreator.setAnnotationInformation(annotationPanel.getAnnotator(), annotationPanel.getRoleInTrail());
			aimCreator.setPatientInformation(parseOpenInventorString(getIvCanvas().get("Patient_Name.string")), 
					parseOpenInventorString(getIvCanvas().get("Patient_ID.string")), 
					parseOpenInventorString(getIvCanvas().get("Patient_Gender.string")));
					
			List<NoduleEntry> items = annotationPanel.getNoduleItems();
			for (int i = 0; i < items.size(); i++){
				List<File> serializedAIMs = new ArrayList<File>();
				
				NoduleEntry item = items.get(i);
				
				String noduleUID = item.getNoduleUID().toString();
				
				File aimFile = new File(String.format("%s\\AVT_AIM_Volume_%s.xml", temp_path, noduleUID));
				File segDcm = new File(String.format("%s\\nodule_%s.dcm", temp_path, noduleUID));

				String seedPoint = item.getNoduleSeedPoint();
				String seedUID = item.getNoduleSeedUID();
				String comment = item.getNoduleDesc().toString();
				if (comment.isEmpty()){
					JOptionPane.showMessageDialog(null, "The Comment is missing!", "Warning", JOptionPane.WARNING_MESSAGE);
				}
				
				if (!seedPoint.isEmpty()){
					aimCreator.marshallVolume(aimFile, seedPoint, seedUID, segDcm, item.getNoduleCharac().toString(), item.getNoduleUncertainy().toString(), 
							comment);
					serializedAIMs.add(aimFile);
					serializedAIMs.add(segDcm);
					
					aimFile = new File(String.format("%s\\AVT_AIM_SEED_%s.xml", temp_path, noduleUID));
					aimCreator.marshallSeed(aimFile, seedPoint, seedUID, segDcm);
					serializedAIMs.add(aimFile);
				}
				
				String recist = item.getNoduleRECIST().toString();
				if (recist.compareToIgnoreCase("0") != 0){
					String recistPoint = item.getNoduleRECISTPoint();
					String recistUID = item.getNoduleRECISTUID();
					aimFile = new File(String.format("%s\\AVT_AIM_RECIST_%s.xml", temp_path, noduleUID));
					aimCreator.marshallRECIST(aimFile, segDcm, recist, recistPoint, recistUID);
					
					serializedAIMs.add(aimFile);
				}
				
				String who = item.getNoduleWHO().toString();
				if (who.compareToIgnoreCase("0") != 0){
					String whoPoint = item.getNoduleWHOPoint();
					String whoUID = item.getNoduleWHOUID();
					aimFile = new File(String.format("%s\\AVT_AIM_WHO_%s.xml", temp_path, noduleUID));
					aimCreator.marshallWHO(aimFile, segDcm, who, whoPoint, whoUID);
					
					serializedAIMs.add(aimFile);
				}
				
				if (bHosted)
					notifyDataAvailable(serializedAIMs);				
				
			}
			msg = "Finished annotation";
		}
		
		//For internal testing		
		if(e.getSource() == annotationPanel.getLoadDataBtn()){
			
			final JFileChooser fc = new JFileChooser();
			JList list = new JList();
			fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			if (JFileChooser.APPROVE_OPTION == fc.showOpenDialog(list)) {
				File file = fc.getSelectedFile();
				
				String dcmFolder = file.getAbsolutePath();
				if(getIvCanvas().set("LoadDicom.name", dcmFolder)){
					System.out.println("Load Dicom files Successfully");
					
					getIvCanvas().set("Dicom_Window.index", "0");
					getIvCanvas().set("Dicom_Level.index", "0");

					getIvCanvas().set("Window_Gate.trigger", "");
					getIvCanvas().set("Level_Gate.trigger", "");
				}
			}
			msg = "Loaded series";
		}
		
		if(e.getSource() == annotationPanel.getLoadBtn()){
			
			JList list = new JList();
			final JFileChooser fc = new JFileChooser();
			fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
			fc.setMultiSelectionEnabled(true); 
			if (JFileChooser.APPROVE_OPTION == fc.showOpenDialog(list)) {
				File[] files = fc.getSelectedFiles(); 
				
				String seriesUID = parseOpenInventorString(getIvCanvas().get("UID_Series.string"));
				NoduleEntry item = buildNoduleItem(files, seriesUID);				
				
				String segFile = item.getSegFile();
				if (segFile.indexOf(".dcm") != -1){
					getIvCanvas().set("Import.inputDcmSeg", item.getSegFile());
					getIvCanvas().set("Import.process", "");
					getIvCanvas().set("Mask_Switch.index", "1");		
					
				}
				else if (segFile.indexOf(".xml") != -1){
					QIBAImageAnnotation qibaAnnotation = new QIBAImageAnnotation();
					List<String> ref_ImageUIDs = new ArrayList<String>();	
					File refFile = new File(segFile);
					qibaAnnotation.getReferredImagesUIDFromXml(refFile, ref_ImageUIDs);
					int num = ref_ImageUIDs.size();
					if ( num <= 0)
						return;
					
					StringBuffer ref_point = new StringBuffer(); 
					StringBuffer ref_pointIndex = new StringBuffer(); 
					qibaAnnotation.OutPutPointsToStrings(refFile, ref_point, ref_pointIndex);		
//					writeString2File(ref_point, "c:/temp/ref_point.txt");
//					writeString2File(ref_pointIndex, "c:/temp/ref_pointIndex.txt");
					
					getIvCanvas().set("QIBA_UID.imageInstanceUID", ref_ImageUIDs.get(0));
					
					getIvCanvas().set("QIBA_Contour.depth", Integer.toString(ref_ImageUIDs.size()));
					getIvCanvas().set("QIBA_Contour.point", ref_point.toString());
					getIvCanvas().set("QIBA_Contour.coordIndex", ref_pointIndex.toString());
					getIvCanvas().set("QIBA_Contour.execute", "");
					getIvCanvas().set("QIBA_Mask.execute", "");

					getIvCanvas().set("Mask_Switch.index", "2");		
					
					getIvCanvas().set("QIBA_Image.points", "[100, 100, 200, 200]");
					getIvCanvas().set("QIBA_Image.sopInstanceUID", ref_ImageUIDs.get(num/2));
					
					getIvCanvas().set("Matrix_Import.index", "2");				
				}
				
				if (!segFile.isEmpty()){
					getIvCanvas().set("Vol_Metrics.update", "");	
					String volume = parseOpenInventorString(getIvCanvas().get("Metrics_Volume.string"));
					item.setNoduleVol(volume);
				}
				
				String recist_uid = item.getNoduleRECISTUID();
				String recist_points = item.getNoduleRECISTPoint();
				if (!recist_uid.isEmpty() && !recist_points.isEmpty()){
					getIvCanvas().set("RECIST_Import.points", recist_points);
					getIvCanvas().set("RECIST_Import.sopInstanceUID", recist_uid);
					
					getIvCanvas().set("RECIST.import", "");
					getIvCanvas().set("RECIST_Switch.whichChild", "0");
					
					getIvCanvas().set("Matrix_Import.index", "0");				
					
				}
				
				String who_uid = item.getNoduleWHOUID();
				String who_points = item.getNoduleWHOPoint();
				if (!who_uid.isEmpty() && !who_uid.isEmpty()){
					getIvCanvas().set("WHO_Import.points", who_points);
					getIvCanvas().set("WHO_Import.sopInstanceUID", who_uid);
					
					getIvCanvas().set("WHO.import", "");
					getIvCanvas().set("WHO_Switch.whichChild", "0");
					
					getIvCanvas().set("Matrix_Import.index", "1");				
				}
				
				annotationPanel.removeAllItems();
				annotationPanel.addNewItem(item);
				annotationPanel.updateNoduleList();				
				
				updateSceneGraph();			
			}
			
			msg = "Loaded annotation";
		}
			
		if (resetCursor)
			setCursor(curCursor);
		
		System.out.println(msg);
		annotationPanel.updateStatusProgressbar(msg);
	}
   private void writeString2File(StringBuffer buffer, String file){
		try {
			FileOutputStream out = new FileOutputStream(new File(file)); 
			out.write(buffer.toString().getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}   	
    }

	private NoduleEntry buildNoduleItem(File[] files, String seriesUID) {
		NoduleEntry item = new NoduleEntry();
		item.setNoduleID("0");
		
	   try{
		   
		   JAXBContext jaxbContext = JAXBContext.newInstance("gme.cacore_cacore._3_2.edu_northwestern_radiology");
		   Unmarshaller u = jaxbContext.createUnmarshaller();
	   
		for (int i = 0; i < files.length; i++){
			
			File aimFile = files[i];
			
			if (aimFile.getName().indexOf(".xml") != -1){	    
				
				   JAXBElement obj = (JAXBElement)u.unmarshal(aimFile);			
				   ImageAnnotation imageAnnotation = ((ImageAnnotation)obj.getValue());
				   String annUID = imageAnnotation.getUniqueIdentifier();
				   
				   String annotationType = imageAnnotation.getCodeValue();
				   
				   if (annotationType.compareToIgnoreCase("AVT001") == 0)
					   continue;
				   
				   //get series instance UID
				   ImageReference imageReference = imageAnnotation.getImageReferenceCollection().getImageReference().get(0);
				   DICOMImageReference ref = (DICOMImageReference) imageReference;
				   Study study = ref.getStudy().getStudy();	 
				   
				   String _seriesUID = study.getSeries().getSeries().getInstanceUID();
				   if (_seriesUID.compareToIgnoreCase(seriesUID) != 0) //validation seriesInstanceUID
					   continue;
					   
				   SegmentationCollection seg = imageAnnotation.getSegmentationCollection();
				   if (seg != null){ //load Dicom segmentation object
					   List<Segmentation> segs = seg.getSegmentation();
					   String uid = segs.get(0).getReferencedSopInstanceUID();
						   String segFile = getDicomSegFile(files, uid);
					   
					   if (!segFile.isEmpty())
						   item.setSegFile(segFile);
					   
					   String comment = segs.get(0).getImagingObservation().getImagingObservation().getComment();
					   if (comment != null)
						   item.setNoduleDesc(comment);
					   
					   List<ImagingObservationCharacteristic> values = segs.get(0).getImagingObservation().getImagingObservation().getImagingObservationCharacteristicCollection().getImagingObservationCharacteristic();
					   for (int k = 0; k < values.size(); k++){
						   String codeID = values.get(k).getCodeValue();
						   String meaning = getShapeMeaning(codeID);
						   if (!meaning.isEmpty())
							   item.setNoduleCharac(meaning);
						   else{
							   meaning = getCertaintyMeaning(codeID);
							   if (!meaning.isEmpty())
								   item.setNoduleUncertainy(meaning);
						   }
					   }
				   }
				   else{
					   CalculationCollection cal = imageAnnotation.getCalculationCollection();
					   if (cal != null){ //load RECIST or WHO
						   List<Calculation> cals = cal.getCalculation();
						   String str = cals.get(0).getCodeMeaning();
						   
						   List<Image> images = study.getSeries().getSeries().getImageCollection().getImage();
						   
						   if (images.size() > 0){
							   String refImageUID = images.get(0).getSopInstanceUID();
							   if (str.compareToIgnoreCase("Long_Axis") == 0 || str.indexOf("Long Axis") != -1)
								   item.setNoduleRECISTUID(refImageUID);
							   if (str.compareToIgnoreCase("CrossProduct") == 0 || str.indexOf("Cross Product") != -1)
								   item.setNoduleWHOUID(refImageUID);
							   
							   List<Calculation> calculations = imageAnnotation.getCalculationCollection().getCalculation();
							   if (calculations.size() > 0){
								   List<CalculationResult> cal_results = calculations.get(0).getCalculationResultCollection().getCalculationResult();
								   if (cal_results.size() > 0){
									   List<Data> data = cal_results.get(0).getDataCollection().getData();
									   float value0 = (float) data.get(0).getValue();
									   
									   if (str.compareToIgnoreCase("Long_Axis") == 0 || str.indexOf("Long Axis") != -1)
										   item.setNoduleRECIST(String.format("%.1f", value0));
									   
									   if (str.compareToIgnoreCase("CrossProduct") == 0 || str.indexOf("Cross Product") != -1){
										   if (data.size() == 1)
											   item.setNoduleWHO(String.format("%.1f", value0));
										   else{
											   float value1 = (float) data.get(1).getValue();
											   item.setNoduleWHO(String.format("%.1f", value0*value1));
										   }
									   }
								   }						   
							   }
						   }
							   
						   List<GeometricShape> shapes = imageAnnotation.getGeometricShapeCollection().getGeometricShape();
						   if (shapes.size() > 0){
							   List<SpatialCoordinate> points = shapes.get(0).getSpatialCoordinateCollection().getSpatialCoordinate();
							   
							   String ptBuffer = "[";
							    for (int j = 0; j < points.size(); j++){
								   TwoDimensionSpatialCoordinate coord = (TwoDimensionSpatialCoordinate) points.get(j);
							       String point = String.format("%d,%d,", (int)coord.getX(), (int)coord.getY());
							       ptBuffer += point;
							    }
							    
							   if (str.compareToIgnoreCase("CrossProduct") == 0 || str.indexOf("Cross Product") != -1){
								   if (shapes.size() > 1){
									   points = shapes.get(1).getSpatialCoordinateCollection().getSpatialCoordinate();		
									    for (int j = 0; j < points.size(); j++) {
											TwoDimensionSpatialCoordinate coord = (TwoDimensionSpatialCoordinate) points
													.get(j);
											String point = String.format(
													"%d,%d,", (int) coord
															.getX(),
													(int) coord.getY());
											ptBuffer += point;
										}									   
								   }
							   }

							    String ptList = ptBuffer.substring(0, ptBuffer.lastIndexOf(","));
							    ptList += "]";
							    
							   if (str.compareToIgnoreCase("Long_Axis") == 0 || str.indexOf("Long Axis") != -1)
								   item.setNoduleRECISTPoint(ptList);
							   if (str.compareToIgnoreCase("CrossProduct") == 0 || str.indexOf("Cross Product") != -1)
								   item.setNoduleWHOPoint(ptList);
							    
						   }
					   }
					   else{ //load CDRH annotation
						   item.setSegFile(aimFile.getAbsolutePath());
					   }
						   
				   }
				   
				}
			}

        } catch (JAXBException e){
            e.printStackTrace();
 	    }    	
        
		return item;
	}

	private String getCertaintyMeaning(String codeID) {
		String radMeaning = "";
		if (codeID.compareToIgnoreCase("RID38") == 0)
			radMeaning = "almost certainly absent";
		
		if (codeID.compareToIgnoreCase("RID32") == 0)
			radMeaning = "almost certainly present";
		
		if (codeID.compareToIgnoreCase("RID39") == 0)
			radMeaning = "definitely not present";
		
		if (codeID.compareToIgnoreCase("RID31") == 0)
			radMeaning = "definitely present";
		
		if (codeID.compareToIgnoreCase("RID34") == 0)
			radMeaning = "possibly present";
		
		if (codeID.compareToIgnoreCase("RID36") == 0)
			radMeaning = "probably not present";
		
		if (codeID.compareToIgnoreCase("RID33") == 0)
			radMeaning = "probably present";
		
		return radMeaning;
	}
	private String getShapeMeaning(String codeID) {
		String radMeaning = "";
		if (codeID.compareToIgnoreCase("RID5808") == 0)
			radMeaning = "asymmetrically shaped";
		
		if (codeID.compareToIgnoreCase("RID5813") == 0)
			radMeaning = "beaded";
		
		if (codeID.compareToIgnoreCase("RID5816") == 0)
			radMeaning = "curved";
		
		if (codeID.compareToIgnoreCase("RID13428") == 0)
			radMeaning = "globular";
		
		if (codeID.compareToIgnoreCase("RID5809") == 0)
			radMeaning = "irregularly shaped";
		
		if (codeID.compareToIgnoreCase("RID5811") == 0)
			radMeaning = "linear";
		
		if (codeID.compareToIgnoreCase("RID5801") == 0)
			radMeaning = "lobular";
		
		if (codeID.compareToIgnoreCase("RID5802") == 0)
			radMeaning = "nodular";
		
		if (codeID.compareToIgnoreCase("RID5800") == 0)
			radMeaning = "ovoid";
		
		if (codeID.compareToIgnoreCase("RID5806") == 0)
			radMeaning = "pedunculated";
		
		if (codeID.compareToIgnoreCase("RID5810") == 0)
			radMeaning = "plate-like";
		
		if (codeID.compareToIgnoreCase("RID5805") == 0)
			radMeaning = "polypoid";
		
		if (codeID.compareToIgnoreCase("RID5799") == 0)
			radMeaning = "round";
		
		if (codeID.compareToIgnoreCase("RID5814") == 0)
			radMeaning = "spoke-wheel";
		
		if (codeID.compareToIgnoreCase("RID5815") == 0)
			radMeaning = "straightened";
		
		if (codeID.compareToIgnoreCase("RID5807") == 0)
			radMeaning = "symmetrically";
		
		if (codeID.compareToIgnoreCase("RID5812") == 0)
			radMeaning = "wedge-shaped";
		
		return radMeaning;
	}
	
	private String getDicomSegFile(File[] files, String uid) {
		for (int i = 0; i < files.length; i++){
			File file = files[i];
			
			if (file.getName().indexOf(".dcm") != -1){
				DicomInputStream segInput = null;
				AttributeList tags = new AttributeList();
				try {
					segInput = new DicomInputStream(file);
					tags.read(segInput);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (DicomException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				// instanceUID
				AttributeTag tag = new AttributeTag(0x08, 0x18);
				Attribute attrib = tags.get(tag);
				if (attrib != null){
					String sopUID = attrib.getDelimitedStringValuesOrEmptyString();
					
					if (sopUID.compareToIgnoreCase(uid) == 0)
						return file.getAbsolutePath();
				
				}			
			}

		}
		return "";
	}

	OutputAvailableListener listener; 
	public void addOutputAvailableListener(OutputAvailableListener l){
		 this.listener = l;
	}
		
	void notifyDataAvailable(List<File> serializedAIMs){
		OutputAvailableEvent event = new OutputAvailableEvent(serializedAIMs);
		listener.outputAvailable(event);
	}

	void setOutputDir(String outDir)
	{
		this.outDir = outDir;
		
		System.out.println(outDir);
	}

	ArrayOfObjectLocator createDataAsFile(List<File> serializedFiles) {
		ArrayOfObjectLocator arrayObjLoc = new ArrayOfObjectLocator();
		List<ObjectLocator> listObjectLocs = arrayObjLoc.getObjectLocator();

		for (int i = 0; i < serializedFiles.size(); i++){
		Uuid objDescUUID = new Uuid();
		objDescUUID.setUuid(UUID.randomUUID().toString());

		ObjectLocator objLoc = new ObjectLocator();				
		objLoc.setUuid(objDescUUID);				
		try {
			objLoc.setUri(serializedFiles.get(i).toURI().toURL().toExternalForm());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}					
		listObjectLocs.add(objLoc);
	}
		
		return arrayObjLoc;
	}
	@Override
	public void selectNoduleAvailable(IASelectionEvent e) {
		NoduleEntry nodule = (NoduleEntry)e.getSource();
		updateNodule(nodule);		
	}
	
	public void resetPanZoom(){
		getIvCanvas().set("Examiner_Axial.mode", "NONE");
		getIvCanvas().set("Examiner_Sagittal.mode", "NONE");
		getIvCanvas().set("Examiner_Coronal.mode", "NONE");

	}
	
	private void updateNodule(NoduleEntry nodule) {
		int noduleIndex = Integer.parseInt(nodule.getNoduleID().toString());
		
		if (noduleIndex < 0)
			return;

		getIvCanvas().set("Mask_Switch.index", "3");
		getIvCanvas().set("Punch_Axial.on", "FALSE");
		
		getIvCanvas().set("RECIST_Switch.whichChild", "-1");
		getIvCanvas().set("RECIST.on", "FALSE");

		getIvCanvas().set("WHO_Switch.whichChild", "-1");
		getIvCanvas().set("WHO.on", "FALSE");

		getIvCanvas().set("RECIST.clear", "");
		getIvCanvas().set("WHO.clear", "");

		String noduleUID = nodule.getNoduleUID().toString();
		String buffer = String.format("%s\\nodule_%s.dcm", temp_path, noduleUID);
		
		File seg = new File(buffer);
		
		if (seg.exists()){
			String clr = annotationPanel.getNoduleClr(noduleIndex);
			if (!clr.isEmpty()){
				getIvCanvas().set("OverlayClr_Axial.color", clr);
				getIvCanvas().set("OverlayClr_Sagittal.color", clr);
				getIvCanvas().set("OverlayClr_Coronal.color", clr);
				
				clr += " 0.5";
				getIvCanvas().set("color.value", clr);
			}

			getIvCanvas().set("Mask_Switch.index", "1");
			
			getIvCanvas().set("Import.inputDcmSeg", buffer);
			getIvCanvas().set("Import.process", "");
		}
			
		String recist = nodule.getRecistVe3f();
		String who = nodule.getWhoVe3f();
		if (!recist.isEmpty()){
			getIvCanvas().set("RECIST_Switch.whichChild", "0");
			getIvCanvas().set("RECIST.points", recist);
			getIvCanvas().set("RECIST.import", "");
			getIvCanvas().set("Matrix_Import.index", "3");				
		}
		
		if (!who.isEmpty()){
			getIvCanvas().set("WHO_Switch.whichChild", "0");
			getIvCanvas().set("WHO.points", who);
			getIvCanvas().set("WHO.import", "");
			getIvCanvas().set("Matrix_Import.index", "4");				
		}

		updateSceneGraph();
		System.out.println(String.format("update tumor: %s", noduleUID));
	}
	private void clearNodule() {
		getIvCanvas().set("Mask_Switch.index", "3");
		getIvCanvas().set("Punch_Axial.on", "FALSE");
		
		getIvCanvas().set("RECIST_Switch.whichChild", "-1");
		getIvCanvas().set("RECIST.on", "FALSE");

		getIvCanvas().set("WHO_Switch.whichChild", "-1");
		getIvCanvas().set("WHO.on", "FALSE");

		getIvCanvas().set("RECIST.clear", "");
		getIvCanvas().set("WHO.clear", "");

		updateSceneGraph();
		System.out.println(String.format("Clear current nodule"));
	}
	
	@Override
	public void updateNoduleAvailable(IASelectionEvent e) {
		NoduleEntry nodule = (NoduleEntry)e.getSource();
		
		int noduleIndex = Integer.parseInt(nodule.getNoduleID().toString());
		if (noduleIndex != -1){
			clearNodule();
			
			Cursor curCursor = new Cursor(Cursor.DEFAULT_CURSOR);
			setCursor(curCursor);
		}		
	}
   
   //utility function to remove the extra quotes
   private String parseOpenInventorString(String str)
   {
		String  strBuf = str;
		
		int index0 = str.indexOf('"');
		int index1 = str.lastIndexOf('"');
		
		if (index0 >= 0 && index1 < str.length())
			strBuf = str.substring(index0+1, index1);    	
		
		return strBuf;
   }
   
   private void updateSceneGraph(){
		Graphics g = getIvCanvas().getGraphics();
		getIvCanvas().update(g);
	}
   
    @Override
	public void itemStateChanged(ItemEvent e) {
		Object source = e.getItemSelectable();

		boolean bSelected = false;
		if (e.getStateChange() == ItemEvent.DESELECTED) {
			bSelected = true;
		}

		if (source == annotationPanel.getContourOverlay()) {
			if (bSelected) {
				getIvCanvas().set("OverlayClr_Axial.transparency", "0.1");
				getIvCanvas().set("OverlayClr_Sagittal.transparency", "0.1");
				getIvCanvas().set("OverlayClr_Coronal.transparency", "0.1");

				getIvCanvas().set("PVol.on", "TRUE");
			} else {
				getIvCanvas().set("OverlayClr_Axial.transparency", "1");
				getIvCanvas().set("OverlayClr_Sagittal.transparency", "1");
				getIvCanvas().set("OverlayClr_Coronal.transparency", "1");

				getIvCanvas().set("PVol.on", "FALSE");
			}
		} else if (source == annotationPanel.getIntersectionLine()) {
			if (bSelected) {
				getIvCanvas()
						.set("Examiner_Axial.intersection", "INTERSECTION");
				getIvCanvas().set("Examiner_Sagittal.intersection",
						"INTERSECTION");
				getIvCanvas().set("Examiner_Coronal.intersection",
						"INTERSECTION");
			} else {
				getIvCanvas().set("Examiner_Axial.intersection", "OFF");
				getIvCanvas().set("Examiner_Sagittal.intersection", "OFF");
				getIvCanvas().set("Examiner_Coronal.intersection", "OFF");
			}
		}
	}
 
	 public Cursor loadCursor(String type, String name) {
		Cursor cursor = null;
		Toolkit tk = Toolkit.getDefaultToolkit();
		try {
			ImageIcon img = new ImageIcon(type);
			cursor = tk.createCustomCursor(img.getImage(), new Point(0, 0),
					name);
		} catch (Exception e) {
			System.out.println("create cursor failed");
		}

		return cursor;

	}

	public void setHostFlag(boolean b) {
		bHosted = b;
		annotationPanel.setHostFlag(b);
	}
}
