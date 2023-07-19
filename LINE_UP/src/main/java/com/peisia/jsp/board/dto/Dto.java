package com.peisia.jsp.board.dto;

public class Dto {
	public String position;         
	public String no;         
	public String name;      
	public String team;       
	public int a_tool;   
	public int b_tool;         
	public int c_tool;        
	public int d_tool;
	public int e_tool;
	public Dto(String position, String name, String team) {
		this.position = position;
		this.name = name;
		this.team = team;
	}  
	//alt + shift + s 코드 자동 삽입
	public Dto(String position, String no, String name, String team, int b_tool, int a_tool, int c_tool, int d_tool,
			int e_tool) {
		this.position = position;
		this.no = no;
		this.name = name;
		this.team = team;
		this.a_tool = a_tool;
		this.b_tool = b_tool;
		this.c_tool = c_tool;
		this.d_tool = d_tool;
		this.e_tool = e_tool;
	}
	public Dto(String name, String team) {
		this.name = name;
		this.team = team;
	}	
}
