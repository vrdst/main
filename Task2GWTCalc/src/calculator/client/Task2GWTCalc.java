package calculator.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Task2GWTCalc implements EntryPoint {
	LayoutPanel calcWindow = new LayoutPanel();
	TextBox result = new TextBox();
	FlexTable buttonSet = new FlexTable(); 
	int i;
	//buttons
	final String[] buttons = new String[]{"C", "^2", "%", "/",
										"7", "8", "9", "*",
										"4", "5", "6", "-",
										"1", "2", "3", "+",
										"+-", "0", ",", "="};
	public void onModuleLoad() {		
		setDefaults();
	}
	
	private void setDefaults(){
		//create buttons and add events
		calcWindow.setSize("190px", "300px");
		result.setSize("180px", "30px");
		buttonSet.setSize("180px", "250px");
		
		for(i = 0 ; i < buttons.length ; i++){

			buttonSet.setWidget(i/4, i%4, new Button(buttons[i], new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					addToResult(buttons[i]);
				}
			}));
			Window.alert(buttons[i]);
		}
		result.setReadOnly(true);
		calcWindow.addStyleName("calc");
		result.addStyleName("result");
		buttonSet.addStyleName("buttonset");
		buttonSet.setStylePrimaryName("buttonset");
		calcWindow.add(result);
		calcWindow.add(buttonSet);
		RootPanel.get("calc").add(calcWindow);
	}
	
	private void addToResult(String textToAdd){
		String currentResult = result.getText();
		double numberResult, firstNumber, secondNumber;

		currentResult = errorCheck(currentResult);
		Window.alert(textToAdd);
		switch (textToAdd) {
		case "0":
			if(currentResult.equals("0")){
				break;
			}
		case "1":
		case "2":
		case "3":
		case "4":
		case "5":
		case "6":
		case "7":
		case "8":
		case "9":
			result.setText(currentResult + textToAdd);
			break;
		case "C":
			result.setText("0");
			break;
		case "^2":
			if(currentResult.length() > 0 && currentResult.substring(currentResult.length() - 1).matches("[0-9]")){
				numberResult = Double.parseDouble(currentResult);
				result.setText(String.valueOf(numberResult * numberResult));
			}
			break;
		case ",":
			addComma(currentResult);
			break;
		case "+-":
			addRemoveSubstractSign(currentResult);
			break;
		case "/":
		case "*":
		case "+":
		case "-":
			numberOperations(textToAdd, currentResult);
			break;
		case "=":
			if(currentResult.split("[/]").length > 1){
				firstNumber = Double.parseDouble(currentResult.split("[/]")[0]);
				secondNumber = Double.parseDouble(currentResult.split("[/]")[1]);
				numberResult = firstNumber / secondNumber;
				result.setText(String.valueOf(numberResult));
			} else if(currentResult.split("[*]").length > 1){
				firstNumber = Double.parseDouble(currentResult.split("[*]")[0]);
				secondNumber = Double.parseDouble(currentResult.split("[*]")[1]);
				numberResult = firstNumber * secondNumber;
				result.setText(String.valueOf(numberResult));
			} else if(currentResult.split("[+]").length > 1){
				firstNumber = Double.parseDouble(currentResult.split("[+]")[0]);
				secondNumber = Double.parseDouble(currentResult.split("[+]")[1]);
				numberResult = firstNumber + secondNumber;
				result.setText(String.valueOf(numberResult));
			} else if(currentResult.split("[-]").length > 1){
				firstNumber = Double.parseDouble(currentResult.split("[-]")[0]);
				secondNumber = Double.parseDouble(currentResult.split("[-]")[1]);
				numberResult = firstNumber - secondNumber;
				result.setText(String.valueOf(numberResult));
			}
			break;
		default:
			break;
		}
		if(currentResult.charAt(0) == '0'){
			result.setText(currentResult.substring(1));
		}
		return;
	}
	


	private void numberOperations(String operator, String currentResult){
		if (currentResult.split(operator).length > 1) {
			if (operator.contains("/") && Double.parseDouble(currentResult.split(operator)[1]) == 0) {
				result.setText("error");
			} else {
				addToResult(buttons[19]);
			} 
		} else if (!hasOperators(currentResult)){ 
			result.setText(currentResult + operator);
		}
	}
	private void addRemoveSubstractSign(String currentResult){
		if(!currentResult.contains("-")){
			result.setText("-" + currentResult);
		} else {
			currentResult.replace("-", "");
		}
	}
	
	private void addComma(String currentResult){
		if(!currentResult.contains("[,]")){
			result.setText(currentResult + ",");
		}
	}
	private String errorCheck(String currentResult) {
		return currentResult.equals("error") ? "0" : currentResult;
	}
	private boolean hasOperators(String inputString){
		if(inputString.contains("-") || inputString.contains("+") || inputString.contains("/") || inputString.contains("*")){
			return true;
		}return false;
	}
}
