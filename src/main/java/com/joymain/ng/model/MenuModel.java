package com.joymain.ng.model;

import java.util.List;

import com.google.common.collect.Lists;

public class MenuModel {
	  protected String action;
	  protected String align;
	  protected String altImage;
	  protected String description;
	  protected String forward;
	  protected String height;
	  protected String image;
	  protected String location;
	  protected String name;
	  protected String onclick;
	  protected String ondblclick;
	  protected String onmouseout;
	  protected String onmouseover;
	  protected String page;
	  protected String roles;
	  protected String target;
	  protected String title;
	  protected String toolTip;
	  protected String width;
	  private String url;
	  protected String onContextMenu;
	  protected String module;
	  private String resCode;
	  
	  List<MenuModel> subMenus = Lists.newArrayList();
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getAlign() {
		return align;
	}
	public void setAlign(String align) {
		this.align = align;
	}
	public String getAltImage() {
		return altImage;
	}
	public void setAltImage(String altImage) {
		this.altImage = altImage;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getForward() {
		return forward;
	}
	public void setForward(String forward) {
		this.forward = forward;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOnclick() {
		return onclick;
	}
	public void setOnclick(String onclick) {
		this.onclick = onclick;
	}
	public String getOndblclick() {
		return ondblclick;
	}
	public void setOndblclick(String ondblclick) {
		this.ondblclick = ondblclick;
	}
	public String getOnmouseout() {
		return onmouseout;
	}
	public void setOnmouseout(String onmouseout) {
		this.onmouseout = onmouseout;
	}
	public String getOnmouseover() {
		return onmouseover;
	}
	public void setOnmouseover(String onmouseover) {
		this.onmouseover = onmouseover;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getToolTip() {
		return toolTip;
	}
	public void setToolTip(String toolTip) {
		this.toolTip = toolTip;
	}
	public String getWidth() {
		return width;
	}
	public void setWidth(String width) {
		this.width = width;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getOnContextMenu() {
		return onContextMenu;
	}
	public void setOnContextMenu(String onContextMenu) {
		this.onContextMenu = onContextMenu;
	}
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	
	public List<MenuModel> getSubMenus() {
		return subMenus;
	}
	public void setSubMenus(List<MenuModel> subMenus) {
		this.subMenus = subMenus;
	}
	
	public void addSubMenu(MenuModel subMenu){
		getSubMenus().add(subMenu);
	}
	@Override
	public String toString() {
		return "MenuModel [action=" + action + ", align=" + align
				+ ", altImage=" + altImage + ", description=" + description
				+ ", forward=" + forward + ", height=" + height + ", image="
				+ image + ", location=" + location + ", name=" + name
				+ ", onclick=" + onclick + ", ondblclick=" + ondblclick
				+ ", onmouseout=" + onmouseout + ", onmouseover=" + onmouseover
				+ ", page=" + page + ", roles=" + roles + ", target=" + target
				+ ", title=" + title + ", toolTip=" + toolTip + ", width="
				+ width + ", url=" + url + ", onContextMenu=" + onContextMenu
				+ ", module=" + module + ", subMenus=" + subMenus + "]";
	}
	public String getResCode() {
		return resCode;
	}
	public void setResCode(String resCode) {
		this.resCode = resCode;
	}
	  
	  
	  
}
