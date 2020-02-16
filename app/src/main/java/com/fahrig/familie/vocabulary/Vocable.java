package com.fahrig.familie.vocabulary;

public class Vocable
{
	public String german;
	public String english;
	
	public Vocable(String german, String english){
		this.german = german;
		this.english = english;
	}
	
	public Vocable(String[] vocable){
		if(vocable.length == 2) {
			this.german = vocable[0];
			this.english = vocable[1];
		} else {
			this.german = "none";
			this.english = "none";
		}
	}
	@Override
    public boolean equals(Object object)
    {
        boolean sameSame = false;
        if (object != null && object instanceof Vocable)
        {
        	if (this.german.equals(((Vocable)object).german) && this.english.equals(((Vocable)object).english)){
	        	sameSame = true;
	        }
        }
        return sameSame;
    }
}
