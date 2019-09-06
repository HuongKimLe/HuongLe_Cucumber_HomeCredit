package interfaceBankguru;

public class AbstractPageUI {
	public static final String DYNAMIC_MENU_LINK = "//a[text() = '%s']";
	public static final String DYNAMIC_TEXTBOX = "//td[text() = '%s']/following-sibling::td/%s";
	public static final String DYNAMIC_TEXTBOX_COINTAINS = "//td[contains(text(),'%s')]/following-sibling::td/%s";
	public static final String DYNAMIC_TABLE= "//td[text() = '%s']/following-sibling::td";
	public static final String DYNAMIC_BUTTON = "//input[@value = '%s']";
	public static final String DYNAMIC_LABEL = "//p[text() ='%s']";
	public static final String DYNAMIC_SELECT_DROPDOWNLIST = "//td[text() = '%s']/following-sibling::td/select";
	public static final String DYNAMIC_CHECKBOX_INPUTFORM = "//td[text() = '%s']/following-sibling::td//input[@value = '%s']";
}
