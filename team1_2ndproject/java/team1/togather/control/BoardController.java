package team1.togather.control;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team1.togather.domain.Board;
import team1.togather.model.BoardService;

@WebServlet("/board/board.do")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String m = request.getParameter("m");
		if(m != null) {
			m = m.trim();
			switch (m) {
				case "list": list(request, response); break;
				case "content": content(request, response); break;
				case "del" : delete(request, response); break;
				case "input" : input(request, response); break;
				case "insert" : insert(request, response); break;
				case "update" : update(request, response); break;
				case "updateDo" : updateDo(request, response); break;
			}
		}else {
			list(request, response);
		}
	}
	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardService service = BoardService.getInstance();
		ArrayList<Board> blist = service.blistS(); // db������ �����
		ArrayList<String> namelist = new ArrayList<>();
		for(Board li: blist) {
			namelist.add(service.getNameByMnumS(li.getMnum()));
		}
		request.setAttribute("blist", blist); // jsp���� ������
		request.setAttribute("namelist", namelist);
		
		String view = "list.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}
	private void content(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardService service = BoardService.getInstance();
		Long bnum = Long.parseLong(request.getParameter("bnum"));
		System.out.println("bnum: "+bnum);
		Board board = service.getContentS(bnum);	
		String name = service.getNameByMnumS(board.getMnum());
		
		request.setAttribute("board", board); // jsp���� ������
		request.setAttribute("name", name);
		service.updateViewS(bnum);
		String view = "content.jsp";
		
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}
	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardService service = BoardService.getInstance();
		Long bnum = Long.parseLong(request.getParameter("bnum"));
		service.deleteS(bnum);
		
		response.sendRedirect("board.do?m=list");
	}
	private void input(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("input.jsp");
	}
	private void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardService service = BoardService.getInstance();
		String btitle = request.getParameter("btitle");
		Long mnum = Long.parseLong(request.getParameter("mnum"));
		String bcategory = request.getParameter("bcategory");
		String bcontent = request.getParameter("bcontent");
		String bfile = request.getParameter("file");
		service.insertS(bcategory, btitle, mnum, bcontent, bfile);
		
		response.sendRedirect("board.do");
	}
	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardService service = BoardService.getInstance();
		Long bnum = Long.parseLong(request.getParameter("bnum"));
		Board board = service.getContentS(bnum);	
		String name = service.getNameByMnumS(board.getMnum());
		
		request.setAttribute("board", board); // jsp���� ������
		request.setAttribute("name", name);
		
		String view = "updateform.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}
	private void updateDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardService service = BoardService.getInstance();
		Long bnum = Long.parseLong(request.getParameter("bnum"));
		String bcategory=request.getParameter("bcategory");
		String btitle = request.getParameter("btitle");
		String bcontent = request.getParameter("bcontent");
		String bfile = request.getParameter("bfile");
		Board board = new Board(bnum, bcategory, btitle, 1, bcontent, bfile, -1, -1, null);
		String name = service.getNameByMnumS(board.getMnum());
		
		service.updateDoS(board);
		
		String view = "board.do";
		response.sendRedirect(view);
	}
}
