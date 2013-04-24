package com.siemens.cmiv.avt.ia;

import java.util.EventObject;

/**
 * @author Jie Zheng
 *
 */

@SuppressWarnings("serial")
public class IASelectionEvent extends EventObject{
	public IASelectionEvent(NoduleEntry source) {
		super(source);
	}

}
