package CalculatorController;

public class Button {
    String type;
    String value;

public Button(String type, String value){
    this.value=value;
    this.type=type;

}

    public String getValue() {
        return value;
    }

    public String getType() {
        return type;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setType(String type) {
        this.type = type;
    }
}
