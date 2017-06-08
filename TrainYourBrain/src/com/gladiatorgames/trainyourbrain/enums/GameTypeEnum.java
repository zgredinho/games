package com.gladiatorgames.trainyourbrain.enums;

public enum GameTypeEnum {
	FIND_ELEMENTS("Find Elements"),
	FIND_PAIRS("Find Pairs"),
	MONKEY_GAME("Monkey Game");
	
	private String name;
	private GameTypeEnum(String name){
		this.name = name;
	}
	
	public static GameTypeEnum getGameTypeByInt(int game){
		switch(game){
		case 1:
			return GameTypeEnum.FIND_ELEMENTS;
		case 2:
			return GameTypeEnum.FIND_PAIRS;
		case 3:
			return GameTypeEnum.MONKEY_GAME;
		default:
			return GameTypeEnum.FIND_ELEMENTS;
		}
	}
	
	@Override
	public String toString(){
		return name;
	}
}
