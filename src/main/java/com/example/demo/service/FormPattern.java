package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("unchecked")
public class FormPattern {
	
	private String InStringData;
	private Integer InIntegerData;
	private List<String> errorKeys = new ArrayList<>();
	@SuppressWarnings({ "rawtypes" })
	private List<Object[]> paramsList = new ArrayList();
	
	
	
//	入力判定処理に引っかかっているか確認して真偽値でコントローラに渡す
	public boolean isFormsCheck() {
		boolean checkForm = true;
		
		for(String key : errorKeys) {
			System.out.println("Infor");
			Pattern p = Pattern.compile("form+");
			Matcher m = p.matcher(key);
			if(m.find()) {
				checkForm = false;
			}
		}
		
		return checkForm;
	}
	
	public List<String> geterrorKeys() {
		return errorKeys;
	}

//	入力判定処理に引っかかったエラーメッセージは最初に引っかかった方を渡す
	public Object[] getParams() {
		Object[] paramsObj = paramsList.get(0);
		return paramsObj;
	}

	public FormPattern(Object inData) {
		if(inData instanceof Integer) {
			InIntegerData = (Integer)inData;
		}else if(inData instanceof String) {
			InStringData = (String)inData;
		}
	}
	

/*--------------------------------------------------------------------------------*/

	
//	入力判定処理（空白及び未入力か判定）
	public FormPattern notBlank() {
		
		if(InStringData != null) {
			boolean blankCheck = InStringData.isEmpty();
			
			if(blankCheck) {
				errorKeys.add("form.notEnter");
				Object[] params = {"空白"};
				paramsList.add(params);
			}
		}
		if(InIntegerData != null){
			boolean blankCheck = String.valueOf(InIntegerData).isEmpty();

			if(blankCheck) {
				errorKeys.add("form.notEnter");
				Object[] params = {"空白"};
				paramsList.add(params);
			}
		}
		return this;
	}
	
	
//	入力判定処理（全角入力か判定）
	public FormPattern notFullWidthCharacter() {
		
		boolean patternCheck = false;
		
		for(char c : InStringData.toCharArray()) {
			patternCheck  = isFullWidth(c);
		}
		
		if(patternCheck) {
			errorKeys.add("form.notEnter");
			Object[] params = {"全角"};
			paramsList.add(params);
		}
		return this;
	}
	
	
//	入力判定処理（文字数制限に引っかかったか判定）
	public FormPattern formLimit(int min, int max) {
		if(InStringData != null) {
			boolean patternCheck = isFormLimit(InStringData, min, max);
			if(!patternCheck) {
				System.out.println("test-limit");
				errorKeys.add("form.outOfRangeCount");
				Object[] params = {min, max};
				paramsList.add(params);
			}
		}
		if(InIntegerData != null) {
			boolean patternCheck = isFormLimit(String.valueOf(InIntegerData), min, max);
			if(!patternCheck) {
				errorKeys.add("form.outOfRangeCount");
				Object[] params = {min, max};
				paramsList.add(params);
			}
		}
		return this;
	}
	
	
	
/*--------------------------------------------------------------------------------*/	
	
	
//	判定処理（全角判定）
	private boolean isFullWidth(char c) {
        return Character.UnicodeBlock.of(c) == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS 
                || Character.UnicodeBlock.of(c) == Character.UnicodeBlock.HIRAGANA 
                || Character.UnicodeBlock.of(c) == Character.UnicodeBlock.KATAKANA;
    }
	
//	判定処理（文字数制限）
	private boolean isFormLimit(String form, int min, int max) {
		int formLength = form.length();
		return min < formLength && formLength < max;
	}
	
}
