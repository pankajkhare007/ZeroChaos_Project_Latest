package com.zc.webdriver.commonLiberaries;

import org.openqa.selenium.By;

public enum LocatorType {
	BY_ID {
		@Override
		public By execute(String id) {
			return By.id(id);
		}

		@Override
		public String getMethodName() {
			return "id";
		}
	},
	BY_LINK_TEXT {
		@Override
		public By execute(String linkText) {
			return By.linkText(linkText);
		}

		@Override
		public String getMethodName() {
			return "linkText";
		}
	}, 
	BY_XPATH {
		@Override
		public By execute(String xpathExpression) {
			return By.xpath(xpathExpression);
		}

		@Override
		public String getMethodName() {
			return "xpath";
		}
	},
	BY_NAME {
		@Override
		public By execute(String xpathExpression) {
			return By.name(xpathExpression);
		}

		@Override
		public String getMethodName() {
			return "name";
		}
	},
	BY_CLASS {
		@Override
		public By execute(String xpathExpression) {
			return By.className(xpathExpression);
		}

		@Override
		public String getMethodName() {
			return "className";
		}
	};
	
	public abstract By execute(String selectorText);
	public abstract String getMethodName();
}
