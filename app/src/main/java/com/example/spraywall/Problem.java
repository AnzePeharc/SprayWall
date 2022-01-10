package com.example.spraywall;

public class Problem {

    public int id;
    public byte[] image;


    public Problem(){
        // Default constructor required for calls to DataSnapshot.getValue(Problem.class)
    }
    public Problem(int id, byte[] image)
    {
        this.id = id;
        this.image = image;

    }

    public int getId()
    {
        return id;
    }
    public byte[] getImage()
    {
        return image;
    }

}
