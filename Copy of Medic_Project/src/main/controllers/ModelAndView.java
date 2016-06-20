package main.controllers;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ModelAndView implements Serializable {
	private String viewName;
	private Map<String, Object> model = new HashMap<String, Object>();
	
	public ModelAndView() {}
	public ModelAndView(String viewName) {
		this.viewName = viewName;
	}
	public ModelAndView(String viewName, String key, Object obj) {
		this(viewName);
		addObject(key, obj);
	}
	
	public String getViewName() {
		return viewName;
	}
	public void setViewName(String viewName) {
		this.viewName = viewName;
	}
	public Map<String, Object> getModel() {
		return model;
	}
	public void setModel(Map<String, Object> model) {
		this.model = model;
	}
	public void addObject(String key, Object obj){
		model.put(key, obj);
	}
	
}
