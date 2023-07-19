package com.peisia.jsp.board.dao;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.peisia.db.Dao;
import com.peisia.db.Db;
import com.peisia.jsp.board.Board;
import com.peisia.jsp.board.dto.Dto;
public class DaoBoard extends Dao{
	/* (1/5)삭제 */
	public void del(String position, String no) {
		super.connect();	//conect()라고 해도 됨.	//[고정1,2,3]
//		connect();
		String sql = String.format("delete from %s where b_no=%s and b_position like '%s'"
				,Board.TABLE_baseballDB, no, position);
		super.update(sql);
		super.close();	//[고정4,5]
	}
	/* (2/5)쓰기 */
	public void insert(Dto d) {
		super.connect();	//[고정1,2,3]
		String sql = String.format(
				"insert into %s (b_position,b_name,b_team,b_a_tool,b_b_tool,b_c_tool,b_d_tool,b_e_tool) values ('%s','%s','%s','%s','%s','%s','%s','%s')"
				,Board.TABLE_baseballDB, d.position,d.name,d.team,d.a_tool,d.b_tool,d.c_tool,d.d_tool,d.e_tool);
		super.update(sql);
		super.close();	//[고정4,5]
	}
	/* (3/5)글 읽기 */
	public Dto selectPost(String position, String no) {
		super.connect();	//[고정1,2,3]
		Dto post = null;
		try {
//			select b_position,b_no,b_title,b_id,b_datetime,b_hit,b_text,b_reply_count,b_reply_ori from ps_board_free;
			String sql = String.format(
					"select b_position,b_name,b_team,b_a_tool,b_b_tool,b_c_tool,b_d_tool,b_e_tool from %s where b_no=%s and b_position like '%s'"
					,Board.TABLE_baseballDB, no, position);
			System.out.println("sql:"+sql);
			ResultSet rs = st.executeQuery(sql);
			rs.next();
			post = new Dto(
					position,
					rs.getString("B_NO"),
					rs.getString("B_NAME"),
					rs.getString("B_TEAM"),
					rs.getInt("B_A_TOOL"),
					rs.getInt("B_B_TOOL"),
					rs.getInt("B_C_TOOL"),
					rs.getInt("B_D_TOOL"),
					rs.getInt("B_E_TOOL")
					);
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.close();	//[고정4,5]
		return post;
	}	
	/* (4/5)글 리스트 */
//	public ArrayList<Dto> selectListBackup(String page) {
//		super.connect();	//[고정1,2,3]
//		ArrayList<Dto> posts = new ArrayList<>();
//		try {
//
//			int startIndex = ((Integer.parseInt(page))-1)*Board.LIST_AMOUNT;
//			
//			//여기에 코딩하시오:
//			//sql 되는거 예문 아래에 복붙해두고 비교해서 작성하시고. (실무에선 혼남. 지울것)
////			select * from ps_board_free where b_no=4;
////			select * from board order by b_no desc limit 20,5;
//			String sql = String.format(
//					"select * from %s limit %s,%s"
//					,Board.TABLE_baseballDB,startIndex,Board.LIST_AMOUNT);
//			System.out.println("sql:"+sql);
//			ResultSet rs = st.executeQuery(sql);
//			while(rs.next()) {				
//				posts.add(new Dto(
//						rs.getString("B_NO"),
//						rs.getString("B_TITLE"),
//						rs.getString("B_ID"),
//						rs.getString("B_DATETIME"),
//						rs.getString("B_HIT"),
//						rs.getString("B_TEXT"),
//						rs.getString("B_REPLY_COUNT"),
//						rs.getString("B_REPLY_ORI")
//						));
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		super.close();	//[고정4,5]
//		return posts;
//	}
	public ArrayList<Dto> selectList(String position, int startIndex) {
		super.connect();	//[고정1,2,3]
		ArrayList<Dto> posts = new ArrayList<>();
		try {
			String sql = String.format(
					"select * from %s where b_position like '%s' limit %d,%d"
					,Board.TABLE_baseballDB
					,position
					,startIndex
					,Board.LIST_AMOUNT);
			System.out.println("sql:"+sql);
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {				
				posts.add(new Dto(
						position,
						rs.getString("B_NO"),
						rs.getString("B_NAME"),
						rs.getString("B_TEAM"),
						rs.getInt("B_A_TOOL"),
						rs.getInt("B_B_TOOL"),
						rs.getInt("B_C_TOOL"),
						rs.getInt("B_D_TOOL"),
						rs.getInt("B_E_TOOL")
						));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.close();	//[고정4,5]
		return posts;
	}
	/* (5/5)수정 */
	public void update(Dto d,String no) {
		super.connect();	//[고정1,2,3]
		String sql = String.format(
				"update %s set b_name='%s',b_team='%s',b_a_tool='%s',b_b_tool='%s',b_c_tool='%s',b_d_tool='%s',b_e_tool='%s' where b_no=%s"
				,Board.TABLE_baseballDB, d.name,d.team,d.a_tool,d.b_tool,d.c_tool,d.d_tool,d.e_tool,no);
		super.update(sql);
		super.close();	//[고정4,5]
	}
	/* 총 글 수 구하기 */
	public int selectPostCount(String position) {
		int count = 0;
		super.connect();	//[고정1,2,3]
		try {
			String sql = String.format(
					"select count(*) from %s where b_position like '%s'"
					,Board.TABLE_baseballDB, position);
			System.out.println("sql:"+sql);//todo
			ResultSet rs = st.executeQuery(sql);
			rs.next();
			count = rs.getInt("count(*)");
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.close();	//[고정4,5]
		return count;
	}
	/* 총 글 수 구하기 */
	public int selectSearchPostCount(String position, String word) {
		int count = 0;
		super.connect();	//[고정1,2,3]
		try {
			String sql = String.format(
					"select count(*) from %s where b_name like '%%%s%%' and b_position like '%s'"
					,Board.TABLE_baseballDB,word,position);
			System.out.println("sql:"+sql);//todo
			ResultSet rs = st.executeQuery(sql);
			rs.next();
			count = rs.getInt("count(*)");
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.close();	//[고정4,5]
		return count;
	}	
	/* 글 리스트<검색> */
	public ArrayList<Dto> selectList(String position, int startIndex, String word) {
		super.connect();	//[고정1,2,3]
		ArrayList<Dto> posts = new ArrayList<>();
		try {
			
			String sql = String.format(
					"select b_position,b_name,b_team,b_a_tool,b_b_tool,b_c_tool,b_d_tool,b_e_tool from %s where b_name like '%%%s%%' and b_position like '%s' limit %s,%s"
					,Board.TABLE_baseballDB,word,position,startIndex,Board.LIST_AMOUNT);
			System.out.println("sql:"+sql);
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {				
				posts.add(new Dto(
						position,
						rs.getString("B_NO"),
						rs.getString("B_NAME"),
						rs.getString("B_TEAM"),
						rs.getInt("B_A_TOOL"),
						rs.getInt("B_B_TOOL"),
						rs.getInt("B_C_TOOL"),
						rs.getInt("B_D_TOOL"),
						rs.getInt("B_E_TOOL")
						));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.close();	//[고정4,5]
		return posts;
	}	
}