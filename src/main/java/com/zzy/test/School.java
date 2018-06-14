package com.zzy.test;

public enum School {
	
	成都信息工程大学(1),四川大学(2);
	
	private int id;

	private School(int id) {
		this.id = id;
	}
	
	public static String getName(int id) {  
        for (School c : School.values()) {  
            if (c.getId() == id) {  
                return c.name();  
            }  
        }  
        return "111";  
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}  
	
}

