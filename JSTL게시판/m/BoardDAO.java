package board.mvc.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import mvc.domain.Board;
import static board.mvc.model.BoardSQL.LIST;
import static board.mvc.model.BoardSQL.INSERT;
import static board.mvc.model.BoardSQL.DELETE;
import static board.mvc.model.BoardSQL.SELECT;

public class BoardDAO {
	private DataSource ds;
	BoardDAO() {
		try{
			Context initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			ds = (DataSource)envContext.lookup("jdbc/myoracle");
		}catch(NamingException ne){
			System.out.println("#tomcat이 만든 dbcp객체 이름(jdbc/myoracle)을 못찾음"); 
		}
	}
	ArrayList<Board> list(){ 
		ArrayList<Board> list = new ArrayList<>();
		ResultSet rs = null;
		Connection con = null;
		Statement stmt = null;
		String sql = LIST; //select * from BOARD order by seq desc
        try{
		   con = ds.getConnection();
		   stmt = con.createStatement();
           rs = stmt.executeQuery(sql);
           while(rs.next()){
              int seq = rs.getInt(1);
              String name = rs.getString(2);
              String email = rs.getString(3);
			  String subject = rs.getString(4);
			  String content = rs.getString(5);
              Date rdate = rs.getDate(6);
              list.add(new Board(seq, name, email, subject, content, rdate));
              //System.out.println(" seq: "+seq+" name: "+name+" email: "+email);
           }
           return list;
        }catch(SQLException se){
        	se.printStackTrace();
        	return null; 
        }finally{
           try{
              if(rs != null) rs.close();
              if(stmt != null) stmt.close();
              if(con != null) con.close();
           }catch(SQLException se){}
        }
	}
	boolean insert(Board dto) {
		  Connection con =null;
		  PreparedStatement pstmt = null;
		  String sql = "insert into BOARD values(BOARD_SEQ.nextval, ?, ?, ?, ?, SYSDATE)";
		  try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getEmail());
			pstmt.setString(3, dto.getSubject());
			pstmt.setString(4, dto.getContent());
			int i = pstmt.executeUpdate(); 
			if(i>0){
				return true; 
			}else{
				return false;
			}
		  }catch(SQLException se){
			  se.printStackTrace();
			  return false;
		  }
	}
	void delete(int seq) {
		Connection con =null;
		PreparedStatement pstmt;
		String sql = "delete from BOARD where SEQ=?";
		 try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, seq);
			pstmt.executeUpdate();
		  }catch(SQLException se){
		  } 
		
	}
	ArrayList<Board> content(Board dto) {
		ArrayList<Board> list = new ArrayList<>();
		Connection con =null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int seq =-1;
		String name ="";
		String email="";
		String subject="";
		String content ="";
		Date rdate=null;
		String sql = "select * from BOARD where SEQ=? order by seq desc";
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, seq);
		    rs = pstmt.executeQuery();
		   while(rs.next()){
		      seq = rs.getInt(1);
		      name = rs.getString(2);
		      email = rs.getString(3);
			  subject = rs.getString(4);
		      content = rs.getString(5);
		      rdate = rs.getDate(6);
		      list.add(new Board(seq, name, email, subject, content, rdate));
		   }
		   return list;
		}catch(SQLException se){
			return null;
        }finally{
           try{
              if(rs != null) rs.close();
           }catch(SQLException se){}
       }
	}
	ArrayList<Board> update(int seq) {
		ArrayList<Board> list = new ArrayList<>();
		  ResultSet rs = null;
		  Connection con =null;
		  PreparedStatement pstmt = null;
		  String name = ""; 
		  String email = "";
		  String subject = "";
		  String content = "";
		  Date rdate = null;
		  String sql = "select * from BOARD where SEQ=? order by seq desc";
		  try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, seq);
			rs = pstmt.executeQuery(); 
				while(rs.next()){ 
					seq = rs.getInt(1);
				      name = rs.getString(2);
				      email = rs.getString(3);
					  subject = rs.getString(4);
				      content = rs.getString(5);
				      rdate = rs.getDate(6);
				      list.add(new Board(seq, name, email, subject, content, rdate));
				}
				return list;
			 }catch(Exception e){
			   System.out.println("5");
			   return null;
			 }	  
	}
	void updateGet(Board dto, String writer) {
		  Connection con =null;
		  Statement stmt = null;
		  String sql = "update BOARD set EMAIL='"+dto.getEmail()+"', WRITER='"+writer+"', SUBJECT='"+dto.getSubject()+"', CONTENT='"+dto.getContent()+"' where SEQ='"+dto.getSeq()+"'";
		  try{
			con = ds.getConnection();
			stmt = con.createStatement();
			stmt.executeUpdate(sql);
		  }catch(SQLException se){
		  }
	}
}
