package com.siemens.cmiv.avt.ia;

import java.awt.Color;


/**
 * @author Jie Zheng
 *
 */
public class NoduleEntry {
	Object noduleID;
	Object noduleClr;
	Object noduleCharac;
	Object noduleUncertainy;
	Object noduleRECIST;
	Object noduleWHO;
	Object noduleVol;
	Object noduleDesc;
	Object noduleUID;
	String seedPoint;
	String seedUID;
	String recistPoint;
	String recistVe3f;
	String recistUID;
	String whoVe3f;
	String whoPoint;
	String whoUID;
	String segFile;

	/**
	 * 
	 */
	public NoduleEntry() {
		noduleID = new String("-1");
		noduleClr = new Color(76, 204, 255);
		noduleCharac = new String("");
		noduleUncertainy = new String("");
		noduleRECIST = new String("");
		noduleWHO = new String("");
		noduleVol = new String("");
		noduleDesc = new String("");
		noduleUID = new String("");
		seedPoint = "";
		seedUID = "";
		recistPoint = "";
		recistUID = "";
		whoPoint = "";
		whoUID = "";
		segFile = "";
		
		recistVe3f = "";
		whoVe3f = "";
	}
	
	public String getRecistVe3f() {
		return recistVe3f;
	}

	public void setRecistVe3f(String recistVe3f) {
		this.recistVe3f = recistVe3f;
	}

	public String getWhoVe3f() {
		return whoVe3f;
	}

	public void setWhoVe3f(String whoVe3f) {
		this.whoVe3f = whoVe3f;
	}

	public String getSegFile(){
		return segFile;
	}
	public Object getNoduleID(){
		return noduleID;
	}
	public Object getNoduleClr(){
		return noduleClr;
	}
	public Object getNoduleCharac(){
		return noduleCharac;
	}
	public Object getNoduleUncertainy(){
		return noduleUncertainy;
	}
	public Object getNoduleRECIST(){
		return noduleRECIST;
	}
	public Object getNoduleWHO(){
		return noduleWHO;
	}
	public Object getNoduleVol(){
		return noduleVol;
	}
	public Object getNoduleDesc(){
		return noduleDesc;
	}
	public Object getNoduleUID(){
		return noduleUID;
	}
	public String getNoduleSeedPoint(){
		return seedPoint;
	}
	public String getNoduleSeedUID(){
		return seedUID;
	}
	public String getNoduleRECISTPoint(){
		return recistPoint;
	}
	public String getNoduleRECISTUID(){
		return recistUID;
	}
	public String getNoduleWHOPoint(){
		return whoPoint;
	}
	public String getNoduleWHOUID(){
		return whoUID;
	}

	public void setSegFile(String segFile){
		this.segFile = segFile;
	}
	public void setNoduleID(String noduleID){
		this.noduleID = new String(noduleID);
	}
	public void setNoduleID(Object noduleID){
		this.noduleID = noduleID;
	}
	public void setNoduleClr(int r, int g, int b){
		this.noduleClr = new Color(r, g, b);
	}
	public void setNoduleClr(Object noduleClr){
		this.noduleClr = noduleClr;
	}
	public void setNoduleCharac(String noduleCharac){
		this.noduleCharac = new String(noduleCharac);
	}
	public void setNoduleCharac(Object noduleCharac){
		this.noduleCharac = noduleCharac;
	}
	public void setNoduleUncertainy(String noduleUncertainy){
		this.noduleUncertainy = new String(noduleUncertainy);
	}
	public void setNoduleUncertainy(Object noduleUncertainy){
		this.noduleUncertainy = noduleUncertainy;
	}
	public void setNoduleRECIST(String noduleRECIST){
		this.noduleRECIST = new String(noduleRECIST);
	}
	public void setNoduleRECIST(Object noduleRECIST){
		this.noduleRECIST = noduleRECIST;
	}
	public void setNoduleWHO(String noduleWHO){
		this.noduleWHO = new String(noduleWHO);
	}
	public void setNoduleWHO(Object noduleWHO){
		this.noduleWHO = noduleWHO;
	}
	public void setNoduleVol(String noduleVol){
		this.noduleVol = new String(noduleVol);
	}
	public void setNoduleVol(Object noduleVol){
		this.noduleVol = noduleVol;
	}
	public void setNoduleDesc(String noduleDesc){
		this.noduleDesc = new String(noduleDesc);
	}
	public void setNoduleDesc(Object noduleDesc){
		this.noduleDesc = noduleDesc;
	}
	public void setNoduleUID(String noduleUID){
		this.noduleUID = new String(noduleUID);
	}
	public void setNoduleUID(Object noduleUID){
		this.noduleUID = noduleUID;
	}
	public void setNoduleSeedPoint(String seedPoint){
		this.seedPoint = seedPoint;
	}
	public void setNoduleSeedReferenceUID(String seedUID){
		this.seedUID = seedUID;
	}
	public void setNoduleRECISTPoint(String recistPoint){
		this.recistPoint = recistPoint;
	}
	public void setNoduleRECISTUID(String recistUID){
		this.recistUID = recistUID;
	}
	public void setNoduleWHOPoint(String whoPoint){
		this.whoPoint = whoPoint;
	}
	public void setNoduleWHOUID(String whoUID){
		this.whoUID = whoUID;
	}
}
