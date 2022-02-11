package team1.togather.model;

import java.util.ArrayList;

import team1.togather.domain.Board;
public class BoardService {
	private BoardDAO dao;
	private static final BoardService instance = new BoardService();
	private BoardService() {
		dao = new BoardDAO();
	}
	public static BoardService getInstance() {
		return instance;
	}
	public ArrayList<Board> blistS(){
		return dao.blist();
	}
	public String getNameByMnumS(Long mnum) {
		return dao.getNameByMnum(mnum);
	}
	public Board getContentS(Long bnum) {
		return dao.getContent(bnum);
	}
	public void deleteS(Long bnum) {
		dao.delete(bnum);
	}
	public void insertS(String bcategory, String btitle, Long mnum, String bcontent, String bfile) {
		dao.insert(bcategory, btitle, mnum ,bcontent, bfile); //���� mnum�� ���ٴ����� �����ִ°� �����̸�
	}
	public void updateDoS(Board dto) {
		dao.updateDo(dto);
	}
	public void updateViewS(Long bnum) {
		dao.updateView(bnum);
	}
}
