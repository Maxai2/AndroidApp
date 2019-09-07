package com.example.alchemy;

public class ElementForAlchemistry {
    public int elemXPos;
    public int elemYPos;
    public String elemName;
    public int elemTag;

    public ElementForAlchemistry() {
    }

    public ElementForAlchemistry(int elemXPos, int elemYPos, String elemName, int elemTag) {
        this.elemXPos = elemXPos;
        this.elemYPos = elemYPos;
        this.elemName = elemName;
        this.elemTag = elemTag;
    }
}
