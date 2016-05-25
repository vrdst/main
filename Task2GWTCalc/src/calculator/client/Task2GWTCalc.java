package calculator.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
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
	
	//buttons
	final String[] buttons = new String[]{"C", "^2", "%", "/",
									"7", "8", "9", "*",
									"4", "5", "6", "-",
									"1", "2", "3", "+",
									"+-", "0", ",", "="};
	
	
	public void onModuleLoad() {		
		calcWindow.setSize("190px", "300px");
		result.setSize("180px", "30px");
		buttonSet.setSize("180px", "250px");
		
		buttonSet.setWidget(0, 0, new Button(buttons[0], new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				addToResult(buttons[0]);
			}
		}));
		
		buttonSet.setWidget(0, 1, new Button(buttons[1], new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				addToResult(buttons[1]);
				}
		}));
		
		buttonSet.setWidget(0, 2, new Button(buttons[2], new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				addToResult(buttons[2]);
				}
		}));
		
		buttonSet.setWidget(0, 3, new Button(buttons[3], new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				addToResult(buttons[3]);
				}
		}));
		
		
		buttonSet.setWidget(1, 0, new Button(buttons[4], new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				addToResult(buttons[4]);
				}
		}));
		
		buttonSet.setWidget(1, 1, new Button(buttons[5], new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				addToResult(buttons[5]);
				}
		}));
		
		buttonSet.setWidget(1, 2, new Button(buttons[6], new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				addToResult(buttons[6]);
				}
		}));
		
		
		buttonSet.setWidget(1, 3, new Button(buttons[7], new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				addToResult(buttons[7]);
				}
		}));
		
		buttonSet.setWidget(2, 0, new Button(buttons[8], new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				addToResult(buttons[8]);
				}
		}));
		
		buttonSet.setWidget(2, 1, new Button(buttons[9], new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				addToResult(buttons[9]);
				}
		}));
		
		
		buttonSet.setWidget(2, 2, new Button(buttons[10], new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				addToResult(buttons[10]);
				}
		}));
		
		buttonSet.setWidget(2, 3, new Button(buttons[11], new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				addToResult(buttons[11]);
				}
		}));
		
		buttonSet.setWidget(3, 0, new Button(buttons[12], new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				addToResult(buttons[12]);
				}
		}));
		
		
		buttonSet.setWidget(3, 1, new Button(buttons[13], new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				addToResult(buttons[13]);
				}
		}));
		
		buttonSet.setWidget(3, 2, new Button(buttons[14], new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				addToResult(buttons[14]);
				}
		}));
		
		buttonSet.setWidget(3, 3, new Button(buttons[15], new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				addToResult(buttons[15]);
			}
		}));
		
		buttonSet.setWidget(4, 0, new Button(buttons[16], new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				addToResult(buttons[16]);
			}
		}));
		
		buttonSet.setWidget(4, 1, new Button(buttons[17], new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				addToResult(buttons[17]);
			}
		}));
		
		buttonSet.setWidget(4, 2, new Button(buttons[18], new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				addToResult(buttons[18]);
			}
		}));
		
		buttonSet.setWidget(4, 3, new Button(buttons[19], new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				addToResult(buttons[19]);
			}
		}));

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
		String currentResult = new String();
		currentResult = result.getText();
		double numberResult;
		double firstNumber;
		double secondNumber;
		
		if (currentResult.equals("error")){
			result.setText("0");
			return;
		}
		
		switch (textToAdd) {
		case "0":
			if(result.getText().equals("0")){
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
			result.setText(result.getText() + textToAdd);
			break;
		case "C":
			result.setText("0");
			break;
		case "^2":
			if(result.getText().length() > 0 && result.getText().substring(result.getText().length() - 1).matches("[0-9]")){
			numberResult = Double.parseDouble(result.getText());
			result.setText(String.valueOf(numberResult * numberResult));
			break;
			} else {
			break;
			}
		case ",":
			if(!result.getText().contains("[,]")){
				result.setText(result.getText() + ",");
			}
			break;
		case "+-":
			if(result.getText().matches("[0-9]")){
				result.setText("-" + result.getText());
			}
			break;
		case "/":
			if(result.getText().contains("[/]")){
				if (result.getText().split("[/]").length > 1) {
					if (Double.parseDouble(result.getText().split("[/]")[1]) == 0) {
						result.setText("error");
						break;
					} else {
						addToResult(buttons[19]);
						break;
					} 
				}
			} else if (!hasOperators(result.getText())){ 
				result.setText(result.getText() + "/");
				}
			break;
		case "*":
			if(result.getText().split("[*]").length > 1){
				addToResult(buttons[19]);
				break;
			} else if (!hasOperators(result.getText())){ 
				result.setText(result.getText() + "*");
			}
			break;
		case "+":
			if(result.getText().split("[+]").length > 1){
				addToResult(buttons[19]);
				break;
			} else if (!hasOperators(result.getText())){ 
				result.setText(result.getText() + "+");
			}
			break;
		case "-":
			if(result.getText().split("[-]").length > 1 ){
				addToResult(buttons[19]);
				break;
			} else if (!hasOperators(result.getText())){ 
				result.setText(result.getText() + "-");
			}
			break;
		case "=":
			if(result.getText().split("[/]").length > 1){
				firstNumber = Double.parseDouble(result.getText().split("[/]")[0]);
				secondNumber = Double.parseDouble(result.getText().split("[/]")[1]);
				numberResult = firstNumber / secondNumber;
				result.setText(String.valueOf(numberResult));
				break;
			} else if(result.getText().split("[*]").length > 1){
				firstNumber = Double.parseDouble(result.getText().split("[*]")[0]);
				secondNumber = Double.parseDouble(result.getText().split("[*]")[1]);
				numberResult = firstNumber * secondNumber;
				result.setText(String.valueOf(numberResult));
				break;
			} else if(result.getText().split("[+]").length > 1){
				firstNumber = Double.parseDouble(result.getText().split("[+]")[0]);
				secondNumber = Double.parseDouble(result.getText().split("[+]")[1]);
				numberResult = firstNumber + secondNumber;
				result.setText(String.valueOf(numberResult));
				break;
			} else if(result.getText().split("[-]").length > 1){
				firstNumber = Double.parseDouble(result.getText().split("[-]")[0]);
				secondNumber = Double.parseDouble(result.getText().split("[-]")[1]);
				numberResult = firstNumber - secondNumber;
				result.setText(String.valueOf(numberResult));
				break;
			}
			break;
		default:
			break;
		}
		if(result.getText().charAt(0) == '0'){
			result.setText(result.getText().substring(1));
		}
		return;
	}
	
	private boolean hasOperators(String inputString){
		if(inputString.contains("-") || inputString.contains("+") || inputString.contains("/") || inputString.contains("*")){
			return true;
		} else {
			return false;
		}
		
	}
	
}
