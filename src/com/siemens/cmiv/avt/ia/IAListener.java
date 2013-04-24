package com.siemens.cmiv.avt.ia;

import java.util.EventListener;

public interface IAListener extends EventListener{
	public void selectNoduleAvailable(IASelectionEvent e);
	public void updateNoduleAvailable(IASelectionEvent e);
}
