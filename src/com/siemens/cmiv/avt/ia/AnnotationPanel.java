package com.siemens.cmiv.avt.ia;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import com.pixelmed.dicom.DicomException;
import com.pixelmed.dicom.UIDGenerator;
import edu.wustl.xipApplication.recist.recistGUI.UnderDevelopmentDialog;

/**
 * @author Jie Zheng
 *
 */

public class AnnotationPanel extends JPanel implements ActionListener, MouseListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JLabel jLabel0 = null;	
	JLabel jLabel01 = null;

	JLabel jLabel1 = null;
	JTextField jTextAnnotator = null;
	
	ButtonGroup roleGroup = new ButtonGroup();  //  @jve:decl-index=0:
	ButtonGroup btnGroup = new ButtonGroup();  //  @jve:decl-index=0:
	
	ANTableModel anTabModel = new ANTableModel();
	List<NoduleEntry> nodules = new ArrayList<NoduleEntry>();
	
	Color xipColor = new Color(51, 51, 102);
	Color textColor = new Color(212, 213, 234);
	Color labelColor = new Color(0, 153, 153);

	int index = 1;
	int roleInTrail = 0;
	
	int noduleLevel = 5;
	private JRadioButton jGTRadioButton = null;

	private JRadioButton jAlgorithmRadioButton = null;

	private JButton jLoadButton = null;

	//get screen resolution
	private int screenHeight = (int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
	private int screenWidth = (int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
	private float heightProportion = 1;
	private float widthProportion = 1;
	//Annotation Field
	JTextField jAnnoTextField = null;
	JRadioButton jAnnoTypeRadioButton1 = null;
	JRadioButton jAnnoTypeRadioButton2 = null;
	
	//AnatomicEntity Field
	JComboBox jComboBoxLoc = null;
	
	//Image Observation Field
	JComboBox jComboBoxNodul = null;
	JComboBox jComboBoxUncertainy = null;
	
	JButton jNewButton = null;
	JButton jDeleteButton = null;
	JButton jClearButton = null;
	
	JButton jSeedButton = null;
	JButton jSemisegButton = null;
	JButton jFreehandButton = null;

	JButton jPunchINButton = null;
	JButton jPunchOUTButton = null;
	JButton jundoButton = null;
	
	JButton jRECISTButton = null;
	JButton jWHOButton = null;
	JButton jVolumeButton = null;

	JTextArea jTextComment = null;
	
	JTable jTabNodule = null;
	//Tool Field 
	JButton jpanzoomButton = null;
	JButton jrotateButton = null;
	JButton jresetButton = null;
	
	JButton jWLBoneButton = null;
	JButton jWLLungButton = null;
	JButton jWLLiverButton = null;
	
	JButton	jLayout31Button = null;
	JButton	jLayout22Button = null;
	JButton	jLayout11Button = null;
	
	JCheckBox jOverlayButton = null;
	JCheckBox jIntersectionButton = null;
	
	// Save Button
	JButton jsaveButton = null;
	
	//internal testing
	JButton jLoadData = null;
	
	JProgressBar jProgressbar = null;
	
	int nTopPosition = 0;
	int nSpacing = 37;
	
	private int curIndex = -1;
	
	public AnnotationPanel(){
		super();
		
		if (screenWidth < 1680)
			widthProportion = (float)(screenWidth / (float)1680);
		if (screenHeight < 1050)
			heightProportion = (float)(screenHeight / (float)1050);
		
		initialize();
	}
	private int getHeightValue(int height){
		return (int)(height * heightProportion);
	}
	private int getWidthValue(int width){
		return (int)(width * widthProportion);
	}
	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		nodules.clear();
		
		setLayout(null);		
				
		JPanel jTitle = new JPanel();
		jTitle.setBackground(xipColor);
		
		jTitle.setBounds(new Rectangle(getWidthValue(0), getHeightValue(0), getWidthValue(350), getHeightValue(103)));
		jTitle.setLayout(null);
		
		jLabel0 = new JLabel();
		jLabel0.setBounds(new Rectangle(getWidthValue(104), getHeightValue(13), getWidthValue(150), getHeightValue(50)));
		jLabel0.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, getHeightValue(45)));
		jLabel0.setText("AVT");
		jLabel0.setBackground(xipColor);
		jLabel0.setForeground(Color.WHITE);
		jTitle.add(jLabel0, null);
		
		jLabel01 = new JLabel();
		jLabel01.setBounds(new Rectangle(getWidthValue(30), getHeightValue(53), getWidthValue(300), getHeightValue(50)));
		jLabel01.setFont(new Font("Arial", Font.BOLD, getHeightValue(22)));
		jLabel01.setText("Image Annotation Tool");
		jLabel01.setBackground(xipColor);
		jLabel01.setForeground(labelColor);
		jTitle.add(jLabel01, null);	
		
		add(jTitle);
		
		// add Annotation Field
		JPanel jAnnotationPanel = new JPanel();
		jAnnotationPanel.setBackground(xipColor);
		jAnnotationPanel.setLayout(null);
		jAnnotationPanel.setBounds(new Rectangle(getWidthValue(0), getHeightValue(150), getWidthValue(330), getHeightValue(85)));
		
		TitledBorder anntationborder;
		anntationborder = BorderFactory.createTitledBorder("Annotation");
		anntationborder.setTitleFont(new Font("",Font.BOLD,getHeightValue(12)));
		anntationborder.setTitleColor(Color.WHITE);
		jAnnotationPanel.setBorder(anntationborder);
		
		JLabel jAnnoNameLabel = new JLabel();
		jAnnoNameLabel.setBounds(new Rectangle(getWidthValue(17), getHeightValue(24), getWidthValue(50), getHeightValue(17)));
		jAnnoNameLabel.setText("Name:");
		jAnnoNameLabel.setFont(new Font("",Font.BOLD,getHeightValue(12)));
		jAnnoNameLabel.setBackground(xipColor);
		jAnnoNameLabel.setForeground(Color.WHITE);
		jAnnotationPanel.add(jAnnoNameLabel, null);
		
		jAnnoTextField = new JTextField();
		jAnnoTextField.setBounds(new Rectangle(getWidthValue(67), getHeightValue(20), getWidthValue(237), getHeightValue(23)));
		jAnnoTextField.setText("Annotation1");
		jAnnoTextField.setFont(new Font("",Font.BOLD,getHeightValue(12)));
		jAnnoTextField.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));
		jAnnotationPanel.add(jAnnoTextField, null);
		
		JLabel jAnnoTypeLabel = new JLabel();
		jAnnoTypeLabel.setBounds(new Rectangle(getWidthValue(17), getHeightValue(52), getWidthValue(50), getHeightValue(17)));
		jAnnoTypeLabel.setText("Type:");
		jAnnoTypeLabel.setFont(new Font("",Font.BOLD,getHeightValue(12)));
		jAnnoTypeLabel.setBackground(xipColor);
		jAnnoTypeLabel.setForeground(Color.WHITE);
		jAnnotationPanel.add(jAnnoTypeLabel, null);
		
		ButtonGroup group = new ButtonGroup();
		
		jAnnoTypeRadioButton1 = new JRadioButton();
		jAnnoTypeRadioButton1.setBounds(new Rectangle(getWidthValue(63), getHeightValue(52), getWidthValue(150), getHeightValue(23)));
		jAnnoTypeRadioButton1.setText("Nominal GroundTruth");
		jAnnoTypeRadioButton1.setFont(new Font("",Font.BOLD,getHeightValue(12)));
		jAnnoTypeRadioButton1.setBackground(xipColor);
		jAnnoTypeRadioButton1.setForeground(Color.WHITE);
		group.add(jAnnoTypeRadioButton1);
		jAnnotationPanel.add(jAnnoTypeRadioButton1, null);
		
		jAnnoTypeRadioButton2 = new JRadioButton();
		jAnnoTypeRadioButton2.setBounds(new Rectangle(getWidthValue(220), getHeightValue(52), getWidthValue(80), getHeightValue(23)));
		jAnnoTypeRadioButton2.setText("Algorithm");
		jAnnoTypeRadioButton2.setFont(new Font("",Font.BOLD,getHeightValue(12)));
		jAnnoTypeRadioButton2.setBackground(xipColor);
		jAnnoTypeRadioButton2.setForeground(Color.WHITE);
		group.add(jAnnoTypeRadioButton2);
		jAnnotationPanel.add(jAnnoTypeRadioButton2, null);
		add(jAnnotationPanel);
		// Annotation Field end
		
		// add AnatomicEntity Field
		TitledBorder anatomicEntityborder;
		anatomicEntityborder = BorderFactory.createTitledBorder("AnatomicEntity");
		anatomicEntityborder.setTitleFont(new Font("",Font.BOLD,getHeightValue(12)));
		anatomicEntityborder.setTitleColor(Color.WHITE);
		
		JPanel jAnatomicpanel = new JPanel();
		jAnatomicpanel.setBorder(anatomicEntityborder);
		jAnatomicpanel.setBounds(new Rectangle(getWidthValue(0), getHeightValue(245), getWidthValue(330), getHeightValue(50)));
		jAnatomicpanel.setLayout(null);
		jAnatomicpanel.setBackground(xipColor);
		
		JLabel jLabelLoc = new JLabel();
		jLabelLoc.setBounds(new Rectangle(getWidthValue(17), getHeightValue(24), getWidthValue(71), getHeightValue(17)));
		jLabelLoc.setText("Location:");
		jLabelLoc.setFont(new Font("",Font.BOLD,getHeightValue(12)));
		jLabelLoc.setBackground(xipColor);
		jLabelLoc.setForeground(Color.WHITE);
		jAnatomicpanel.add(jLabelLoc, null);
		
		jComboBoxLoc = new JComboBox();
		jComboBoxLoc.setBounds(new Rectangle(getWidthValue(91), getHeightValue(20), getWidthValue(213), getHeightValue(23)));
		jComboBoxLoc.setEditable(false);
		jComboBoxLoc.setFont(new Font("",Font.BOLD,getHeightValue(12)));
		jComboBoxLoc.addItem("Abdomen");
		jComboBoxLoc.addItem("Head");
		jComboBoxLoc.addItem("Lower extremity");
		jComboBoxLoc.addItem("Neck");
		jComboBoxLoc.addItem("Nervous system");
		jComboBoxLoc.addItem("Spine");
		jComboBoxLoc.addItem("Thorax");
		jComboBoxLoc.addItem("Trunk");
		jComboBoxLoc.addItem("Upper extremity");
		jComboBoxLoc.addItem("Blood vessel");
		
		jAnatomicpanel.add(jComboBoxLoc, null);
		
		add(jAnatomicpanel);
		// AnatomicEntity Field end
		
		// add Image Observation Field
		TitledBorder imageObservationborder;
		imageObservationborder = BorderFactory.createTitledBorder("Image Observations");
		imageObservationborder.setTitleFont(new Font("",Font.BOLD,getHeightValue(12)));
		imageObservationborder.setTitleColor(Color.WHITE);
		
		JPanel jimageObservation = new JPanel();
		jimageObservation.setBackground(xipColor);
		jimageObservation.setLayout(null);
		jimageObservation.setBounds(new Rectangle(getWidthValue(0), getHeightValue(305), getWidthValue(330), getHeightValue(350)));
		jimageObservation.setBorder(imageObservationborder);
		
		jTabNodule = new JTable(anTabModel);
		jTabNodule.setPreferredScrollableViewportSize(new Dimension(getWidthValue(300), getHeightValue(100)));
		jTabNodule.setFont(new Font("",Font.BOLD,getHeightValue(12)));
		JTableHeader jTableHeader = jTabNodule.getTableHeader();
		jTableHeader.setFont(new Font("",Font.BOLD,getHeightValue(12)));
		jTabNodule.setFillsViewportHeight(true);
		jTabNodule.setShowGrid(true);
		
		//set color item
		jTabNodule.setDefaultRenderer(Color.class, new ColorRenderer(true));
		jTabNodule.setDefaultEditor(Color.class, new ColorEditor());
		
		//set selection
		jTabNodule.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jTabNodule.getSelectionModel().addListSelectionListener(new RowListener());

		//setup combo box
		setUpCharacteristicColumn(jTabNodule, jTabNodule.getColumnModel().getColumn(2));
		setUpUncertainyColumn(jTabNodule, jTabNodule.getColumnModel().getColumn(3));
		
		//set column width
		jTabNodule.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		setUpColumnWidth(jTabNodule);
		
		JScrollPane scrollPane = new JScrollPane(jTabNodule);
		scrollPane.setBounds(new Rectangle(getWidthValue(12), getHeightValue(20), getWidthValue(300), getHeightValue(150)));
		scrollPane.setAutoscrolls(true);
		jimageObservation.add(scrollPane, null);

		nTopPosition = 190;
		jimageObservation.add(getNewBtn(), null);
		jimageObservation.add(getDeleteBtn(), null);
		
		jClearButton = new JButton();
		jClearButton.setBounds(new Rectangle(getWidthValue(224), getHeightValue(nTopPosition), getWidthValue(100), getHeightValue(30)));
		jClearButton.setText("Clear");
		jClearButton.setFont(new Font("", Font.BOLD, getHeightValue(12)));
		jClearButton.setBackground(xipColor);
		jClearButton.setForeground(Color.WHITE);
		jClearButton.setEnabled(false);
		jimageObservation.add(jClearButton, null);

		jSeedButton = new JButton();
		jSeedButton.setBounds(new Rectangle(getWidthValue(7), getHeightValue(nTopPosition+nSpacing), getWidthValue(100), getHeightValue(30)));
		jSeedButton.setText("Add Seed");
		jSeedButton.setFont(new Font("", Font.BOLD, getHeightValue(12)));
		jSeedButton.setBackground(xipColor);
		jSeedButton.setForeground(Color.WHITE);
		jSeedButton.setEnabled(false);
		jimageObservation.add(jSeedButton, null);
		
		jSemisegButton = new JButton();
		jSemisegButton.setBounds(new Rectangle(getWidthValue(117), getHeightValue(nTopPosition+nSpacing), getWidthValue(100), getHeightValue(30)));
		jSemisegButton.setText("Semi-seg");
		jSemisegButton.setFont(new Font("", Font.BOLD, getHeightValue(12)));
		jSemisegButton.setBackground(xipColor);
		jSemisegButton.setForeground(Color.WHITE);
		jSemisegButton.setEnabled(false);
		jimageObservation.add(jSemisegButton, null);
		
		jFreehandButton = new JButton();
		jFreehandButton.setBounds(new Rectangle(getWidthValue(224), getHeightValue(nTopPosition+nSpacing), getWidthValue(100), getHeightValue(30)));
		jFreehandButton.setText("Free-hand");
		jFreehandButton.setFont(new Font("", Font.BOLD, getHeightValue(12)));
		jFreehandButton.setBackground(xipColor);
		jFreehandButton.setForeground(Color.WHITE);
		jFreehandButton.setEnabled(false);
		jimageObservation.add(jFreehandButton, null);
		
		jPunchINButton = new JButton();
		jPunchINButton.setBounds(new Rectangle(getWidthValue(7), getHeightValue(nTopPosition+2*nSpacing), getWidthValue(100), getHeightValue(30)));
		jPunchINButton.setText("Punch IN");
		jPunchINButton.setFont(new Font("", Font.BOLD, getHeightValue(12)));
		jPunchINButton.setBackground(xipColor);
		jPunchINButton.setForeground(Color.WHITE);
		jPunchINButton.setEnabled(false);
		jimageObservation.add(jPunchINButton, null);
		
		jPunchOUTButton = new JButton();
		jPunchOUTButton.setBounds(new Rectangle(getWidthValue(117), getHeightValue(nTopPosition+2*nSpacing), getWidthValue(100), getHeightValue(30)));
		jPunchOUTButton.setText("Punch OUT");
		jPunchOUTButton.setFont(new Font("", Font.BOLD, getHeightValue(12)));
		jPunchOUTButton.setBackground(xipColor);
		jPunchOUTButton.setForeground(Color.WHITE);
		jPunchOUTButton.setEnabled(false);
		jimageObservation.add(jPunchOUTButton, null);
		
		jundoButton = new JButton();
		jundoButton.setBounds(new Rectangle(getWidthValue(224), getHeightValue(nTopPosition+2*nSpacing), getWidthValue(100), getHeightValue(30)));
		jundoButton.setText("Undo");
		jundoButton.setFont(new Font("", Font.BOLD, getHeightValue(12)));
		jundoButton.setBackground(xipColor);
		jundoButton.setForeground(Color.WHITE);
		jundoButton.setEnabled(false);
		jimageObservation.add(jundoButton, null);

		jRECISTButton = new JButton();
		jRECISTButton.setBounds(new Rectangle(getWidthValue(7), getHeightValue(nTopPosition+3*nSpacing), getWidthValue(100), getHeightValue(30)));
		jRECISTButton.setText("RECIST");
		jRECISTButton.setFont(new Font("", Font.BOLD, getHeightValue(12)));
		jRECISTButton.setBackground(xipColor);
		jRECISTButton.setForeground(Color.WHITE);
		jRECISTButton.setEnabled(false);
		jimageObservation.add(jRECISTButton, null);
		
		jWHOButton = new JButton();
		jWHOButton.setBounds(new Rectangle(getWidthValue(117), getHeightValue(nTopPosition+3*nSpacing), getWidthValue(100), getHeightValue(30)));
		jWHOButton.setText("WHO");
		jWHOButton.setFont(new Font("", Font.BOLD, getHeightValue(12)));
		jWHOButton.setBackground(xipColor);
		jWHOButton.setForeground(Color.WHITE);
		jWHOButton.setEnabled(false);
		jimageObservation.add(jWHOButton, null);
		
		jVolumeButton = new JButton();
		jVolumeButton.setBounds(new Rectangle(getWidthValue(224), getHeightValue(nTopPosition+3*nSpacing), getWidthValue(100), getHeightValue(30)));
		jVolumeButton.setText("Done");
		jVolumeButton.setFont(new Font("", Font.BOLD, getHeightValue(12)));
		jVolumeButton.setBackground(xipColor);
		jVolumeButton.setForeground(Color.WHITE);
		jVolumeButton.setEnabled(false);
		jimageObservation.add(jVolumeButton, null);

		add(jimageObservation);
		// Image Observation end
		
		// add Tool Field
		int nToolPosition = 665;
		JTabbedPane jTabPanel = new JTabbedPane(JTabbedPane.TOP,JTabbedPane.SCROLL_TAB_LAYOUT);
		jTabPanel.setBounds(new Rectangle(getWidthValue(0), getHeightValue(nToolPosition), getWidthValue(330), getHeightValue(115)));

		JPanel jToolPanel = new JPanel();
//		jToolPanel.setBorder(toolborder);
		jToolPanel.setBounds(new Rectangle(getWidthValue(0), getHeightValue(nToolPosition), getWidthValue(330), getHeightValue(105)));
		jToolPanel.setLayout(null);
		jToolPanel.setBackground(xipColor);
		
		jpanzoomButton = new JButton();
		jpanzoomButton.setBounds(new Rectangle(getWidthValue(5), getHeightValue(10), getWidthValue(100), getHeightValue(30)));
		jpanzoomButton.setText("Pan/Zoom");
		jpanzoomButton.setFont(new Font("", Font.BOLD, getHeightValue(12)));
		jpanzoomButton.setBackground(xipColor);
		jpanzoomButton.setForeground(Color.WHITE);
		jpanzoomButton.setEnabled(true);
		jToolPanel.add(jpanzoomButton, null);
		
		jrotateButton = new JButton();
		jrotateButton.setBounds(new Rectangle(getWidthValue(115), getHeightValue(10), getWidthValue(100), getHeightValue(30)));
		jrotateButton.setText("Rotate");
		jrotateButton.setFont(new Font("", Font.BOLD, getHeightValue(12)));
		jrotateButton.setBackground(xipColor);
		jrotateButton.setForeground(Color.WHITE);
		jrotateButton.setEnabled(true);
		jToolPanel.add(jrotateButton, null);
		
		jresetButton = new JButton();
		jresetButton.setBounds(new Rectangle(getWidthValue(222), getHeightValue(10), getWidthValue(100), getHeightValue(30)));
		jresetButton.setText("Reset");
		jresetButton.setFont(new Font("", Font.BOLD, getHeightValue(12)));
		jresetButton.setBackground(xipColor);
		jresetButton.setForeground(Color.WHITE);
		jresetButton.setEnabled(true);
		jToolPanel.add(jresetButton, null);
		
		jWLBoneButton = new JButton();
		jWLBoneButton.setBounds(new Rectangle(getWidthValue(5), getHeightValue(47), getWidthValue(100), getHeightValue(30)));
		jWLBoneButton.setText("W/L-Bone");
		jWLBoneButton.setFont(new Font("", Font.BOLD, getHeightValue(12)));
		jWLBoneButton.setBackground(xipColor);
		jWLBoneButton.setForeground(Color.WHITE);
		jWLBoneButton.setEnabled(true);
		jToolPanel.add(jWLBoneButton, null);
		
		jWLLungButton = new JButton();
		jWLLungButton.setBounds(new Rectangle(getWidthValue(115), getHeightValue(47), getWidthValue(100), getHeightValue(30)));
		jWLLungButton.setText("W/L-Lung");
		jWLLungButton.setFont(new Font("", Font.BOLD, getHeightValue(12)));
		jWLLungButton.setBackground(xipColor);
		jWLLungButton.setForeground(Color.WHITE);
		jWLLungButton.setEnabled(true);
		jToolPanel.add(jWLLungButton, null);
		
		jWLLiverButton = new JButton();
		jWLLiverButton.setBounds(new Rectangle(getWidthValue(222), getHeightValue(47), getWidthValue(100), getHeightValue(30)));
		jWLLiverButton.setText("W/L-Liver");
		jWLLiverButton.setFont(new Font("", Font.BOLD, getHeightValue(12)));
		jWLLiverButton.setBackground(xipColor);
		jWLLiverButton.setForeground(Color.WHITE);
		jWLLiverButton.setEnabled(true);
		jToolPanel.add(jWLLiverButton, null);
		
//		add(jToolPanel);
		JLabel jToolLabel = new JLabel("Tool");
		jToolLabel.setFont(new Font("",Font.BOLD,getHeightValue(12)));
		jTabPanel.addTab(null, jToolPanel);
		jTabPanel.setTabComponentAt(0, jToolLabel);
		
		//the setting panel
		JPanel settingPanel = new JPanel();
		settingPanel.setLayout(null);
		settingPanel.setBounds(new Rectangle(getWidthValue(0), getHeightValue(nToolPosition), getWidthValue(330), getHeightValue(105)));
		settingPanel.setBackground(xipColor);

		jLayout31Button = new JButton();
		jLayout31Button.setBounds(new Rectangle(getWidthValue(5), getHeightValue(10), getWidthValue(100), getHeightValue(30)));
		jLayout31Button.setText("Layout_3x1");
		jLayout31Button.setFont(new Font("", Font.BOLD, getHeightValue(12)));
		jLayout31Button.setBackground(xipColor);
		jLayout31Button.setForeground(Color.WHITE);
		jLayout31Button.setEnabled(true);
		settingPanel.add(jLayout31Button, null);
		
		jLayout22Button = new JButton();
		jLayout22Button.setBounds(new Rectangle(getWidthValue(115), getHeightValue(10), getWidthValue(100), getHeightValue(30)));
		jLayout22Button.setText("Layout_2x2");
		jLayout22Button.setFont(new Font("", Font.BOLD, getHeightValue(12)));
		jLayout22Button.setBackground(xipColor);
		jLayout22Button.setForeground(Color.WHITE);
		jLayout22Button.setEnabled(true);
		settingPanel.add(jLayout22Button, null);
		
		jLayout11Button = new JButton();
		jLayout11Button.setBounds(new Rectangle(getWidthValue(222), getHeightValue(10), getWidthValue(100), getHeightValue(30)));
		jLayout11Button.setText("Layout_1x1");
		jLayout11Button.setFont(new Font("", Font.BOLD, getHeightValue(12)));
		jLayout11Button.setBackground(xipColor);
		jLayout11Button.setForeground(Color.WHITE);
		jLayout11Button.setEnabled(true);
		settingPanel.add(jLayout11Button, null);

		jOverlayButton = new JCheckBox("Hide Overlay Contour");
		jOverlayButton.setBounds(new Rectangle(getWidthValue(5), getHeightValue(47), getWidthValue(150), getHeightValue(30)));
		jOverlayButton.setFont(new Font("", Font.BOLD, getHeightValue(12)));
		jOverlayButton.setBackground(xipColor);
		jOverlayButton.setForeground(Color.WHITE);
		settingPanel.add(jOverlayButton, null);
		
		jIntersectionButton = new JCheckBox("Hide Intersection Line");
		jIntersectionButton.setBounds(new Rectangle(getWidthValue(165), getHeightValue(47), getWidthValue(150), getHeightValue(30)));
		jIntersectionButton.setFont(new Font("", Font.BOLD, getHeightValue(12)));
		jIntersectionButton.setBackground(xipColor);
		jIntersectionButton.setForeground(Color.WHITE);
		settingPanel.add(jIntersectionButton, null);
		
		JLabel jSettingLabel = new JLabel("Setting");
		jSettingLabel.setFont(new Font("",Font.BOLD,getHeightValue(12)));
		jTabPanel.addTab(null, settingPanel);
		jTabPanel.setTabComponentAt(1, jSettingLabel);
		
		add(jTabPanel);
		// Tool Field end
		
		// add Save Button Field
		jsaveButton = new JButton();
		jsaveButton.setBounds(new Rectangle(getWidthValue(224), getHeightValue(833), getWidthValue(95), getHeightValue(30)));
		jsaveButton.setText("Save");
		jsaveButton.setFont(new Font("", Font.BOLD, getHeightValue(12)));
		jsaveButton.setBackground(xipColor);
		jsaveButton.setForeground(Color.WHITE);
		jsaveButton.setEnabled(false);
		add(jsaveButton, null);
		// Save Button end
		
		add(getLoadBtn(), null);
		
		// add Button Field
		jLoadData = new JButton();
		jLoadData.setBounds(new Rectangle(getWidthValue(24), getHeightValue(833), getWidthValue(95), getHeightValue(30)));
		jLoadData.setText("load series");
		jLoadData.setFont(new Font("", Font.BOLD, getHeightValue(12)));
		jLoadData.setBackground(xipColor);
		jLoadData.setForeground(Color.WHITE);
		jLoadData.setEnabled(true);
		
		add(jLoadData, null);		
		
		// add Process Bar Field
		jProgressbar = new JProgressBar();
		jProgressbar.setBounds(new Rectangle(getWidthValue(4), getHeightValue(900), getWidthValue(320), getHeightValue(30)));
		jProgressbar.setBackground(new Color(156, 162, 189));
		jProgressbar.setFont(new Font("", Font.BOLD, getHeightValue(12)));
		jProgressbar.setStringPainted(true);
		jProgressbar.setForeground(xipColor);
		jProgressbar.setString("Ready");
		jProgressbar.setEnabled(true);
		add(jProgressbar);
		// Process Bar Field end
		
		setBackground(xipColor);
		
		addMouseListener(this);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//processed by the parent panel		
	}
	
	int adjustForResolution(){
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); 
		int height = (int)screenSize.getHeight();
		int preferredHeight = 200;
		if (height < 768 && height >= 600 ){
			preferredHeight = 100;
		}else if(height < 1024 && height >= 768 ){
			preferredHeight = 100;
		}else if (height >= 1024 && height < 1200){
			preferredHeight = 200;
		}else if(height > 1200 && height <= 1440){
			preferredHeight = 200;
		}
		return preferredHeight;		
	}
	
    public void setUpCharacteristicColumn(JTable table,
            TableColumn column) {
		//Set up the editor for the sport cells.
		JComboBox comboBox = new JComboBox();
		comboBox.addItem("asymmetrically shaped");
		comboBox.addItem("beaded");
		comboBox.addItem("curved");
		comboBox.addItem("globular");
		comboBox.addItem("irregularly shaped");
		comboBox.addItem("linear");
		comboBox.addItem("lobular");
		comboBox.addItem("nodular");
		comboBox.addItem("ovoid");
		comboBox.addItem("pedunculated");
		comboBox.addItem("plate-like");
		comboBox.addItem("polypoid");
		comboBox.addItem("round");
		comboBox.addItem("spoke-wheel");
		comboBox.addItem("straightened");
		comboBox.addItem("symmetrically");
		comboBox.addItem("wedge-shaped");
		column.setCellEditor(new DefaultCellEditor(comboBox));
		
		//Set up tool tips for the sport cells.
		DefaultTableCellRenderer renderer =
		new DefaultTableCellRenderer();
		renderer.setToolTipText("Click for nodule characteristics selection");
		column.setCellRenderer(renderer);
	}
	
    public void setUpUncertainyColumn(JTable table,
            TableColumn column) {
		//Set up the editor for the sport cells.
		JComboBox comboBox = new JComboBox();
		comboBox.addItem("almost certainly absent");
		comboBox.addItem("almost certainly present");
		comboBox.addItem("definitely not present");
		comboBox.addItem("definitely present");
		comboBox.addItem("possibly present");
		comboBox.addItem("probably not present");
		comboBox.addItem("probably present");
		column.setCellEditor(new DefaultCellEditor(comboBox));
		
		//Set up tool tips for the sport cells.
		DefaultTableCellRenderer renderer =
		new DefaultTableCellRenderer();
		renderer.setToolTipText("Click for nodule uncertainy selection");
		column.setCellRenderer(renderer);
	}
    
    public void setUpColumnWidth(JTable table){
    	int nCol = table.getColumnCount();
    	
    	for (int i = 0; i < nCol; i++){
    		TableColumn col = table.getColumnModel().getColumn(i);
    		
    		int width = 100;
    		switch (i){
    		case 0:
    			width = 30;
    			break;
    			
    		case 1:
    			width = 50;
    			break;
    			
    		case 2:
    			width = 100;
    			break;
    			
    		case 3:
    			width = 120;
    			break; 			    			
    		}
    		
    		col.setPreferredWidth(width);
    	}
    }

    private class RowListener implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent event) {
            if (event.getValueIsAdjusting()) {
                return;
            }
            int selectedCol = jTabNodule.getSelectedColumn();
            if (selectedCol != 0)
            	return;
            
            int selectedRow = jTabNodule.getSelectedRow();
            if (selectedRow >= 0){
            	if (isSameItem(selectedRow))
            		return;
            	
            	curIndex = selectedRow;
				if(!validateCurrentItem()){
					fireNoduleAvailable(nodules.get(curIndex));
					return;
				}
				
				fireSelectionAvailable(nodules.get(curIndex));
            
				System.out.print(String.format("Selected Row: %d\n", selectedRow));
            }
        }
    }

    @SuppressWarnings("serial")
	class ANTableModel extends AbstractTableModel {
		String[] strArrayColumnNames = {
				"ID",
				"Color",
				"Shape",
				"Certainty",
				"RECIST(mm)",
				"WHO(mm2)",
				"Volume(ml)", 
				"Comment"
			};   
		public int getColumnCount() {			
			return strArrayColumnNames.length;
		}

		public int getRowCount() {
			return nodules.size();
		}

		public Object getValueAt(int rowIndex, int columnIndex) {
			switch( columnIndex ) {
                //
				case 0:
                	return  nodules.get(rowIndex).getNoduleID();
                	
				//
				case 1:
                	return nodules.get(rowIndex).getNoduleClr();
                	
    			//
				case 2:
                	return nodules.get(rowIndex).getNoduleCharac();
                	
    			//
				case 3:
                	return nodules.get(rowIndex).getNoduleUncertainy();
 
                //
				case 4:
					return nodules.get(rowIndex).getNoduleRECIST();
					
				case 5:
					return nodules.get(rowIndex).getNoduleWHO();
					
				case 6:
					return nodules.get(rowIndex).getNoduleVol();
					
				case 7:
					return nodules.get(rowIndex).getNoduleDesc();
					
				default:
                    return null;
            }           			
		}
		
        public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }
        
        public boolean isCellEditable(int row, int col) {
            //Note that the data/cell address is constant,
            //no matter where the cell appears onscreen.
            if (col < 1) {
                return false;
            } else {
                return true;
            }
        }

      public void setValueAt(Object value, int row, int col) {
			switch( col ) {
			case 0:
				nodules.get(row).setNoduleID(value);
				break;
				
			case 1:
				nodules.get(row).setNoduleClr(value);
				break;
				
			case 2:
				nodules.get(row).setNoduleCharac(value);
			break;
				
			case 3:
				nodules.get(row).setNoduleUncertainy(value);
				break;
				
			case 4:
				nodules.get(row).setNoduleRECIST(value);
				break;
				
			case 5:
				nodules.get(row).setNoduleWHO(value);
				break;
				
			case 6:
				nodules.get(row).setNoduleVol(value);
				break;
				
			case 7:
				nodules.get(row).setNoduleDesc(value);
				break;
			}
				
            fireTableCellUpdated(row, col);
        }
				
		public String getColumnName( int col ) {
            return strArrayColumnNames[col];
        }
		
		public int getColumnWidth( int nCol ) {
            switch( nCol ) {
                case 0:
                    return 50;
                case 1:
                    return 100;
                 default:
                    return 150;
            }
        }		
	}	

	List<NoduleEntry> getNodules()
	{
		return nodules;
	}
	
	public String getAnnotator()
	{
		return jAnnoTextField.getText();
	}
	
	public String getComment()
	{
		return jTextComment.getText();
	}
	
	public int getNoduleLevel()
	{
		return noduleLevel;
	}
	
	public JButton getNewBtn(){
		if (jNewButton == null) {
			jNewButton = new JButton();
			jNewButton.setBounds(new Rectangle(getWidthValue(7), getHeightValue(nTopPosition), getWidthValue(100), getHeightValue(30)));
			jNewButton.setText("New");
			jNewButton.setFont(new Font("", Font.BOLD, getHeightValue(12)));
			jNewButton.setBackground(xipColor);
			jNewButton.setForeground(Color.WHITE);
			jNewButton.setEnabled(true);
			jNewButton.addMouseListener(new java.awt.event.MouseAdapter() {
				@Override
				public void mousePressed(java.awt.event.MouseEvent e) {
					if (curIndex != -1){
						if(!validateCurrentItem())
							return;

						fireNoduleAvailable(nodules.get(curIndex));
					}
					
					NoduleEntry nodule = new NoduleEntry();
						
					nodule.setNoduleID(Integer.toString(nodules.size()));
					nodule.setNoduleClr(new Color(76, 204, 255));
					nodule.setNoduleCharac("TBD");
					nodule.setNoduleUncertainy("TBD");
					
					UIDGenerator uid = new UIDGenerator();
					try {
						nodule.setNoduleUID(uid.getNewUID());
					} catch (DicomException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					nodules.add(nodule);
					curIndex = nodules.size() - 1;
					
					updateNoduleList();
					
					getDeleteBtn().setEnabled(true);
					updateButtonStatus(true);
				}
			});
		}
		return jNewButton;
	}
	public JButton getDeleteBtn(){
		if (jDeleteButton == null) {
			jDeleteButton = new JButton();
			jDeleteButton.setBounds(new Rectangle(getWidthValue(117), getHeightValue(nTopPosition), getWidthValue(100), getHeightValue(30)));
			jDeleteButton.setText("Delete");
			jDeleteButton.setFont(new Font("", Font.BOLD, getHeightValue(12)));
			jDeleteButton.setBackground(xipColor);
			jDeleteButton.setForeground(Color.WHITE);
			jDeleteButton.setEnabled(false);
			jDeleteButton.addMouseListener(new java.awt.event.MouseAdapter() {
				@Override
				public void mousePressed(java.awt.event.MouseEvent e) {
					if (curIndex != -1){
						fireNoduleAvailable(nodules.get(curIndex));
						
						nodules.remove(curIndex);
						updateNoduleList();							
					}
					
					curIndex = nodules.size() - 1;

					getSegmentBtn().setEnabled(false);
					if (curIndex == -1){
						updateButtonStatus(false);
						updateContourStatus(false);
					}
				}
			});
		}
		return jDeleteButton;
	}	
	public JButton getClearBtn(){
		return jClearButton;
	}
	
	public JButton getSeedBtn(){
		return jSeedButton;
	}	
	public JButton getSegmentBtn(){
		return jSemisegButton;
	}	
	public JButton getFreehandBtn(){
		return jFreehandButton;
	}	

	public JButton getPunchINBtn(){
		return jPunchINButton;
	}
	public JButton getPunchOUTBtn(){
		return jPunchOUTButton;
	}
	public JButton getUndoBtn(){
		return jundoButton;
	}
	
	public JButton getRECISTBtn(){
		return jRECISTButton;
	}
	public JButton getWHOBtn(){
		return jWHOButton;
	}
	public JButton getVolumeBtn(){
		return jVolumeButton;
	}
	
	public JButton getPanZoomBtn(){
		return jpanzoomButton;
	}
	public JButton getRotationBtn(){
		return jrotateButton;
	}
	public JButton getResetBtn(){
		return jresetButton;
	}
	
	public JButton getWLBoneBtn(){
		return jWLBoneButton;
	}
	public JButton getWLLungBtn(){
		return jWLLungButton;
	}
	public JButton getWLLiverBtn(){
		return jWLLiverButton;
	}
	
	public JButton getSaveBtn(){
		return jsaveButton;
	}
	public int getRoleInTrail(){
		return roleInTrail;
	}
	public JButton getLoadDataBtn(){
		return jLoadData;
	}
	
	public JButton getLayout31Btn(){
		return jLayout31Button;
	}
	public JButton getLayout22Btn(){
		return jLayout22Button;
	}
	public JButton getLayout11Btn(){
		return jLayout11Button;
	}
	
	public JCheckBox getContourOverlay(){
		return jOverlayButton;
	}	
	public JCheckBox getIntersectionLine(){
		return jIntersectionButton;
	}
	
	public void updateStatusProgressbar(String msg){
		jProgressbar.setString(msg);
	}
	
	public void updateButtonStatus(boolean bEnable){
		getSeedBtn().setEnabled(bEnable);
		getClearBtn().setEnabled(bEnable);
		getRECISTBtn().setEnabled(bEnable);
		getWHOBtn().setEnabled(bEnable);
		getVolumeBtn().setEnabled(bEnable);
	}
	
	public void updateContourStatus(boolean bEnable){
		getFreehandBtn().setEnabled(bEnable);
		getPunchINBtn().setEnabled(bEnable);
		getPunchOUTBtn().setEnabled(bEnable);
		getUndoBtn().setEnabled(bEnable);
	}

	/**
	 * This method initializes jGTRadioButton	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getJGTRadioButton() {
		if (jGTRadioButton == null) {
			jGTRadioButton = new JRadioButton();
			jGTRadioButton.setBounds(new Rectangle(86, 55, 145, 19));
			jGTRadioButton.setText("Nominal GroundTruth");
			jGTRadioButton.setBackground(xipColor);
			jGTRadioButton.setForeground(Color.WHITE);
			jGTRadioButton.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					roleInTrail = 0; 
				}
			});
	  			
			jGTRadioButton.setSelected(true);
			roleGroup.add(jGTRadioButton);
		}
		return jGTRadioButton;
	}
	/**
	 * This method initializes jAlgorithmRadioButton	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getJAlgorithmRadioButton() {
		if (jAlgorithmRadioButton == null) {
			jAlgorithmRadioButton = new JRadioButton();
			jAlgorithmRadioButton.setBounds(new Rectangle(228, 55, 85, 19));
			jAlgorithmRadioButton.setBackground(xipColor);
			jAlgorithmRadioButton.setText("Algorithm");
			jAlgorithmRadioButton.setForeground(Color.WHITE);
			jAlgorithmRadioButton.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					roleInTrail = 1; 
				}
			});

			roleGroup.add(jAlgorithmRadioButton);
		}
		return jAlgorithmRadioButton;
	}
	/**
	 * This method initializes jLoadButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton getLoadBtn() {
		if (jLoadButton == null) {
			jLoadButton = new JButton();
			jLoadButton.setBounds(new Rectangle(getWidthValue(124), getHeightValue(833), getWidthValue(95), getHeightValue(30)));
			jLoadButton.setText("Load");
			jLoadButton.setFont(new Font("", Font.BOLD, getHeightValue(12)));
			jLoadButton.setBackground(xipColor);
			jLoadButton.setForeground(Color.WHITE);
			
			jLoadButton.setVisible(true);
		}
		return jLoadButton;
	}
	
	public void updateNoduleList() {
		anTabModel.fireTableDataChanged();
	}	
	
	IAListener listener;
    public void addIAListener(IAListener l) {        
        listener = l;          
    }
    
	void fireSelectionAvailable(NoduleEntry result){
		IASelectionEvent event = new IASelectionEvent(result);         		
        listener.selectNoduleAvailable(event);
	}
	void fireNoduleAvailable(NoduleEntry result){
		IASelectionEvent event = new IASelectionEvent(result);         		
        listener.updateNoduleAvailable(event);
	}

	public String getCurNoduleClr(){
        String noduleColor = "";
        if (curIndex == -1){
        	Color clr = (Color)nodules.get(curIndex).getNoduleClr();
        	
        	float r = (float) ((float)clr.getRed() / 255.0);
        	float g = (float) ((float)clr.getGreen() / 255.0);
        	float b = (float) ((float)clr.getBlue() / 255.0);
        	
        	noduleColor = String.format("%.1f %.1f %.1f", r, g, b);       	
        }
        
        return noduleColor;
	}
	
	public String getCurNoduleUID(){
	    if (curIndex != -1){
	    	NoduleEntry item = nodules.get(curIndex);
		
	    	return item.getNoduleUID().toString();
	    }
	    
	    return "";
	}
	
	public String getNoduleClr(int noduleIndex) {
        String noduleColor = "";
        
        if (noduleIndex < nodules.size()){
         	Color clr = (Color)nodules.get(noduleIndex).getNoduleClr();
        	
        	float r = (float) ((float)clr.getRed() / 255.0);
        	float g = (float) ((float)clr.getGreen() / 255.0);
        	float b = (float) ((float)clr.getBlue() / 255.0);
        	
        	noduleColor = String.format("%.1f %.1f %.1f", r, g, b);       	
        }
        
        return noduleColor;
	}
	
	public List<String> getNoduleUIDs(){
		List<String> UIDs = new ArrayList<String>();
		
		for (int i = 0; i < nodules.size(); i++){
			String uid = nodules.get(i).noduleUID.toString();
			UIDs.add(uid);
		}
		return UIDs;
	}
	
	public List<NoduleEntry> getNoduleItems(){
		return nodules;
	}
	
	public void removeAllItems(){
		nodules.clear();
		curIndex = -1;
	}
	
	public void addNewItem(NoduleEntry item){
		nodules.add(item);
		curIndex = nodules.size() - 1;
		
		if (curIndex != -1)
			getDeleteBtn().setEnabled(true);
	}
	
	public void updateCurrentItem(String recist, String recistPoint, String recistUID, String who, String whoPoint, String whoUID, String volume, 
			String recistPoints, String whoPoints) {
	 	    if (curIndex != -1){
				nodules.get(curIndex).setNoduleRECIST(recist);
				nodules.get(curIndex).setNoduleWHO(who);
				nodules.get(curIndex).setNoduleVol(volume);
				nodules.get(curIndex).setNoduleRECISTPoint(recistPoint);
				nodules.get(curIndex).setNoduleRECISTUID(recistUID);
				nodules.get(curIndex).setNoduleWHOPoint(whoPoint);
				nodules.get(curIndex).setNoduleWHOUID(whoUID);
				
				nodules.get(curIndex).setRecistVe3f(recistPoints);
				nodules.get(curIndex).setWhoVe3f(whoPoints);
				
				updateNoduleList();				
		    }
	}
	
	public void updateItemSeed(String points, String referenceUID) {
	    if (curIndex != -1){
			nodules.get(curIndex).setNoduleSeedPoint(points);
			nodules.get(curIndex).setNoduleSeedReferenceUID(referenceUID);
	    }
}
	public boolean isSameItem(int item){
		if (curIndex == item)
			return true;
		
		return false;
	}
	public boolean validateCurrentItem() {
		if (curIndex == -1)
			return false;
		
		NoduleEntry item = nodules.get(curIndex);
		String recist = item.getNoduleRECIST().toString();
		String who = item.getNoduleWHO().toString();
		String shape = item.getNoduleCharac().toString();
		String certainty = item.getNoduleUncertainy().toString();

		String msg = "Please set ";
		if (shape.compareToIgnoreCase("TBD") == 0)
			msg += "Nodule shape";
		else if (certainty.compareToIgnoreCase("TBD") == 0)
			msg += "Nodule certainty";
		else if (recist.isEmpty() || who.isEmpty())
			msg += "RECIST/WHO measurement";
		
		if (msg.compareToIgnoreCase("Please set ") != 0){
			Object[] options = {"OK"}; 
            JOptionPane.showOptionDialog(this, msg,
                    "Warning",  
                    JOptionPane.OK_OPTION,  
                    JOptionPane.WARNING_MESSAGE,  
                    null,  
                    options,  
                    options[0]);
			
			return false;
		}

		return true;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		Cursor currentCursor = new Cursor(Cursor.DEFAULT_CURSOR);
		setCursor(currentCursor);
	}
	@Override
	public void mouseExited(MouseEvent e) {
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void setHostFlag(boolean flag){
		jLoadData.setVisible(!flag);
	}
}
