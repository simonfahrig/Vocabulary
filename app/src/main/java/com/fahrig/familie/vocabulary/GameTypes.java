package com.fahrig.familie.vocabulary;

import android.content.res.Resources;

public class GameTypes {

    // Attributes
	public static final int ADDITION_RESULT = 0;
	public static final int LATIN5_LECTION1_RESULT = 1;
	public static final int LATIN5_LECTION2_RESULT = 2;
	public static final int LATIN5_LECTION3_RESULT = 3;
	public static final int LATIN5_LECTION4_RESULT = 4;

    // Methoden
    public static String getLabel(Resources resources, int id){
    	switch (id) {
			case ADDITION_RESULT:
				return resources.getString(R.string.addition);
			case LATIN5_LECTION1_RESULT:
                return resources.getString(R.string.latin5_lection1);
	    	case LATIN5_LECTION2_RESULT:
                return resources.getString(R.string.latin5_lection2);
			case LATIN5_LECTION3_RESULT:
                return resources.getString(R.string.latin5_lection3);
			case LATIN5_LECTION4_RESULT:
                return resources.getString(R.string.latin5_lection4);
			default:
			    return "";
        }
    }

    public static int getId(Resources resources, String label){
        if (label.equals(resources.getString(R.string.addition))){
            return ADDITION_RESULT;
        } else if (label.equals(resources.getString(R.string.latin5_lection1))){
            return LATIN5_LECTION1_RESULT;
        } else if (label.equals(resources.getString(R.string.latin5_lection2))){
            return LATIN5_LECTION2_RESULT;
        } else if (label.equals(resources.getString(R.string.latin5_lection3))){
            return LATIN5_LECTION3_RESULT;
        } else if (label.equals(resources.getString(R.string.latin5_lection4))){
            return LATIN5_LECTION4_RESULT;
        } else {
            return -1;
        }
    }

    public static String[] getList(Resources resources, int id){
        switch (id) {
            case ADDITION_RESULT:
                return resources.getStringArray(R.array.addition);
            case LATIN5_LECTION1_RESULT:
                return resources.getStringArray(R.array.latin5_lection1);
            case LATIN5_LECTION2_RESULT:
                return resources.getStringArray(R.array.latin5_lection2);
            case LATIN5_LECTION3_RESULT:
                return resources.getStringArray(R.array.latin5_lection3);
            case LATIN5_LECTION4_RESULT:
                return resources.getStringArray(R.array.latin5_lection4);
            default:
                return new String[]{};
        }
    }

    public static String[] getList(Resources resources, String label){
        if (label.equals(resources.getString(R.string.addition))){
            return resources.getStringArray(R.array.addition);
        } else if (label.equals(resources.getString(R.string.latin5_lection1))){
            return resources.getStringArray(R.array.latin5_lection1);
        } else if (label.equals(resources.getString(R.string.latin5_lection2))){
            return resources.getStringArray(R.array.latin5_lection2);
        } else if (label.equals(resources.getString(R.string.latin5_lection3))){
            return resources.getStringArray(R.array.latin5_lection3);
        } else if (label.equals(resources.getString(R.string.latin5_lection4))){
            return resources.getStringArray(R.array.latin5_lection4);
        } else {
            return new String[]{};
        }
    }

    public static Boolean isMathGame(int id){
        if(id == ADDITION_RESULT){
            return true;
        } else {
            return false;
        }
    }
}
