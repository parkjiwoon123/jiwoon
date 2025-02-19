package controller;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import moa.Moamember;
import moa.PriceData;
import moa.Product;
import moa.SomeDatabaseService;
import moa.UserVO;
import moa.gogo;
import moa.gogoVO;
import phonebook.Pagelist;
import phonebook.PhonebookOracleDAO;
import phonebook.PhonebookService;
import phonebook.PhonebookVO;
import replyboard.BoardService;
import replyboard.BoardVO;
import replyboard.BoardpageList;


@Controller
public class MainController{
	@Autowired
	MemberService service;
	@Autowired
	PhonebookService phonebookservice;
	@Autowired
	BoardService boardservice;
	@Autowired
	 SomeDatabaseService databaseService; 
	 
	
	
	@GetMapping("/12")
	public String healthCheak() {

	        return "healthCheak";
	    }
	 @RequestMapping("/")
	    public ModelAndView index() {
	        ModelAndView mv=new ModelAndView();
	        mv.addObject("mainpage","/home/main.jsp");
	        mv.setViewName("index");
	        return mv;
	    }
	 @RequestMapping("/login")
	 	public ModelAndView login() {
		
		 ModelAndView mv=new ModelAndView();
		 mv.setViewName("login/login");
		 return mv;
}
	 @RequestMapping("/index")
	  public ModelAndView indexgo() {
	        ModelAndView mv=new ModelAndView();
	        mv.addObject("mainpage","/home/main");
	        mv.setViewName("index");
	        return mv;
	    }
	 @RequestMapping("/logout")
	 public ModelAndView logout(HttpSession session) {
		 session.invalidate();
		 ModelAndView mv=new ModelAndView();
		 mv.setViewName("login/login");
		 return mv;
}
	 @RequestMapping("/loginProc")
	 public ModelAndView loginproc(String id,String password,HttpSession session) {
		 ModelAndView mv=new ModelAndView();
		if(service.loginchek(id, password)) {
			
			session.setAttribute("id", id);
			session.setAttribute("password", password);
			mv.addObject("mainpage","/home/main.jsp");
			mv.setViewName("index");
	 }
		else {
			mv.setViewName("login/login");
			
		}
		return mv;
	 }
		@RequestMapping("/member/joinmember")
		public ModelAndView joinmember() {
			ModelAndView mv=new ModelAndView();
			mv.setViewName("/member/joinmember");
			return mv;
		}
		@RequestMapping("/member/joinmemberproc")
		public ModelAndView joinmemberproc(String id,String password,String email,String name){ {
			MemberVO VO = new MemberVO(id,password,email,name);
			int result = service.joinMember(VO);
			ModelAndView mv=new ModelAndView();
			if(result>0) {
				mv.addObject("mainpage", "member/membersuc.jsp");
				mv.setViewName("index");
			}
			else{
				mv.addObject("mainpage", "member/memberfail.jsp");
				mv.setViewName("index");
			}
			return mv;
		}
		}
		@RequestMapping("/update")
		public 	ModelAndView update(String id) {
			ModelAndView mv=new ModelAndView();
			mv.addObject("id",id);
			mv.addObject("mainpage","member/update.jsp");
			mv.setViewName("index");
			return mv;
		}
		@RequestMapping("/updateproc")
		public ModelAndView updateproc(String id,String password,String email,String name) {
			MemberVO VO = new MemberVO(id,password,email,name);
			int result = service.updateMember(VO);
			ModelAndView mv=new ModelAndView();
			if(result>0) {
				mv.addObject("mainpage", "member/updatesuc.jsp");
				mv.setViewName("index");
			}
			else{
				mv.addObject("mainpage", "member/updatefail.jsp");
				mv.setViewName("index");
			}
			return mv;
		}
		@RequestMapping("/company")
		public ModelAndView company() {
			ModelAndView mv=new ModelAndView();
			mv.addObject("mainpage","drool-html/index.jsp");
			mv.setViewName("index");
			return mv;
		
		}
		@RequestMapping("/phonebook")
		public ModelAndView phonebook(Integer currentPage) {
			
			if(currentPage==null) {
				currentPage=1;
			}
			
			Pagelist pagelist=phonebookservice.pageList(currentPage);
			System.out.println(pagelist.getTotalPage());
			ModelAndView mv=new ModelAndView();
			mv.addObject("pagelist",pagelist);
			mv.addObject("mainpage","phonebook/pagelist.jsp");
			mv.setViewName("index");
			return mv;
			
		}
		@RequestMapping("/phupdate")
		public ModelAndView phupdate(int idx,String name,String hp,String memo) {
			ModelAndView mv=new ModelAndView();
			PhonebookVO phonebookvo = new PhonebookVO(idx,name,hp,memo);
			mv.addObject("update",phonebookvo);
			mv.addObject("mainpage", "phonebook/update.jsp");
			mv.setViewName("index");
			return mv;
			
		}
		@RequestMapping("/phwrite")
		public ModelAndView phwrite() {
			ModelAndView mv = new ModelAndView();

			mv.addObject("mainpage","phonebook/writeForm.jsp");
			mv.setViewName("index");
			return mv;
		}
		@RequestMapping("/phwriteProc")
		public ModelAndView phwriteproc(String name,String number,String hp,Integer currentPage) {
			phonebookservice.insert(name, number, hp);
			if(currentPage==null) {
				currentPage=1;
			}
			Pagelist pagelist=phonebookservice.pageList(currentPage);
			System.out.println(pagelist.getTotalPage());
			ModelAndView mv=new ModelAndView();
			mv.addObject("pagelist",pagelist);
			mv.addObject("mainpage","phonebook/pagelist.jsp");
			mv.setViewName("index");		
			
			return mv;
		}
		@RequestMapping("/phupdateProc")
		public ModelAndView phupdateproc(int idx,String name,String hp,String memo,Integer currentPage) {
			PhonebookVO phonebookvo = new PhonebookVO(idx,name,hp,memo);
			phonebookservice.update(phonebookvo);
			System.out.println(phonebookvo.toString());
			if(currentPage==null) {
				currentPage=1;
			}
			
			Pagelist pagelist=phonebookservice.pageList(currentPage);
			System.out.println(pagelist.getTotalPage());
			
			
			ModelAndView mv=new ModelAndView();
			mv.addObject("pagelist",pagelist);
			mv.addObject("mainpage","phonebook/pagelist.jsp");
			mv.setViewName("index");
			return mv;
		}
		
		@RequestMapping("/viewOne")
		public ModelAndView view(int idx) {
		    PhonebookVO phonebookvo = phonebookservice.findone(idx);
		    
		    ModelAndView mv = new ModelAndView();
		   
		    mv.addObject("board", phonebookvo);
		    mv.addObject("mainpage", "phonebook/view.jsp");
		    mv.setViewName("index");
		    return mv;
		}

		@RequestMapping("/del")
		public ModelAndView del(Integer idx,Integer currentPage) {
			
			if(idx != null) {
		   System.out.println(phonebookservice.del(idx));
			}
			if(currentPage==null) {
				currentPage=1;
			}
		    Pagelist pagelist=phonebookservice.pageList(currentPage);
			
			ModelAndView mv=new ModelAndView();
			mv.addObject("pagelist",pagelist);
			mv.addObject("mainpage","phonebook/pagelist.jsp");
			mv.setViewName("index");
		    return mv;
		}
		
		
		@RequestMapping("/board")
		public ModelAndView board(Integer currentPage) {
			
			if(currentPage==null) {
				currentPage=1;
			}
			
			int countPerPage=10;
			BoardpageList boardpageList
			=boardservice.pageList(currentPage, countPerPage);
			 ModelAndView mv = new ModelAndView();
			 mv.addObject("pagelist",boardpageList);
			 mv.addObject("mainpage","replyboard/pageList.jsp");
			 mv.setViewName("index");
			 return mv;
		}
		@RequestMapping("/findView")
		public ModelAndView findView(int idx) {
			BoardVO board=boardservice.findOneById(idx);
			 ModelAndView mv = new ModelAndView();
			 mv.addObject("board",board);
			 mv.addObject("mainpage", "replyboard/findOne.jsp");
			 mv.setViewName("index");
			 return mv;
		}
		@RequestMapping("/write")
		public ModelAndView write() {
			ModelAndView mv = new ModelAndView();

			mv.addObject("mainpage","replyboard/writeForm.jsp");
			mv.setViewName("index");
			return mv;
		}
	    @RequestMapping(value = "/writeProc", method = RequestMethod.POST)
	    public ModelAndView writeProc(
	            @RequestParam("writename") String writename,
	            @RequestParam("title") String title,
	            @RequestParam("content") String content,
	            @RequestParam("filename") MultipartFile file,
	            RedirectAttributes redirectAttributes,
	            HttpServletRequest request,
	            HttpServletResponse response) {

	        // Set request and response encoding to UTF-8 to prevent character encoding issues
	        try {
	            request.setCharacterEncoding("UTF-8");
	            response.setCharacterEncoding("UTF-8");
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        // Handle file upload
	        String filename = null;
	        if (!file.isEmpty()) {
	            try {
	                byte[] bytes = file.getBytes();
	                filename = file.getOriginalFilename();
	                Path path = Paths.get("C:\\Users\\admin\\eclipse-workspace\\replyboard\\src\\main\\webapp\\WEB-INF\\fileupload", filename);
	                Files.write(path, bytes);
	            } catch (IOException e) {
	                e.printStackTrace();
	                ModelAndView mv = new ModelAndView("index");
	                redirectAttributes.addFlashAttribute("message", "File upload failed. Please try again.");
	                return mv;
	            }
	        }

	        // Prepare data
	        BoardVO board = new BoardVO();
	        board.setWritename(writename);
	        board.setWriteid(writename);
	        board.setTitle(title);
	        board.setContent(content);
	        board.setFilename(filename);

	        // Send data to the service
	        try {
	            boardservice.insert(board);
	        } catch (Exception e) {
	            e.printStackTrace();
	            ModelAndView mv = new ModelAndView("index");
	            redirectAttributes.addFlashAttribute("message", "Failed to insert board entry. Please try again.");
	            return mv;
	        }

	        int countPerPage = 10;
	        BoardpageList boardpageList = boardservice.pageList(1, countPerPage);
	        ModelAndView mv = new ModelAndView();
	        mv.addObject("pagelist", boardpageList);
	        mv.addObject("mainpage", "replyboard/pageList.jsp");
	        mv.setViewName("index");

	        return mv;
	    }
	
		@RequestMapping("/updateProc")
		public ModelAndView boardupdateProc(int idx,String title,String content) {
			BoardVO board=new  BoardVO();
			board.setIdx(idx);
			board.setTitle(title);
			board.setContent(content);
			//System.out.println(board.toString());
			boardservice.update(board);
			ModelAndView mv = new ModelAndView();
			board=boardservice.findOneById(idx);
			 mv.addObject("board",board);
			 mv.addObject("mainpage", "replyboard/findOne.jsp");
			 mv.setViewName("index");
			 return mv;
			 
		}
		
		@RequestMapping("/deleteProc")
		public ModelAndView deleteProc(int idx) {
			
			 ModelAndView mv = new ModelAndView();
			 
			boardservice.del(idx);
			int countPerPage=10;
			BoardpageList boardpageList
			=boardservice.pageList(1, countPerPage);
			mv.addObject("pagelist",boardpageList);
			  mv.addObject("mainpage","replyboard/pageList.jsp");
			mv.setViewName("index");
			return mv;
		}
		@RequestMapping("/replyForm")
		public ModelAndView replyForm(int idx) {
			BoardVO parent = boardservice.findOneById(idx);
			ModelAndView mv = new ModelAndView();
			mv.addObject("parent",parent);
			mv.addObject("mainpage", "replyboard/replyForm.jsp");
			mv.setViewName("index");
			return mv;
		}
		@RequestMapping("/replyFormProc")
		public ModelAndView replyFormProc(String title,String content,String writename,int tab,int parentid) {
			tab = tab+1;
			int currentPage =1;
			String filename ="";
			BoardVO board=new  BoardVO();
			board.setWritename(writename);
			board.setWriteid(writename);
			board.setTitle(title);
			board.setContent(content);
			board.setFilename(filename);
			board.setTab(tab);
			board.setParentid(parentid);
			boardservice.insertreply(board);
			int countPerPage=10;
			BoardpageList boardpageList
			=boardservice.pageList(currentPage, countPerPage);
			 ModelAndView mv = new ModelAndView();
			 mv.addObject("pagelist",boardpageList);
			 mv.addObject("mainpage","replyboard/pageList.jsp");
			 mv.setViewName("index");
			
			 return mv;
		}
		@RequestMapping("/chat")
		public ModelAndView chat(HttpSession session) {
		    ModelAndView mv = new ModelAndView();
		    try {
		        mv.addObject("id", session.getAttribute("id"));
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		    mv.addObject("mainpage", "chat.jsp");
		    mv.setViewName("index");
		    return mv;
		}
		@RequestMapping("/gallery")
		public ModelAndView gallery() {
			  ModelAndView mv = new ModelAndView();
			  mv.addObject("mainpage","gallery.jsp");
			  mv.setViewName("index");
			return mv;
			  
		}
		@RequestMapping("/mail")
		public ModelAndView mail() {
			ModelAndView mv = new ModelAndView();
			mv.addObject("mainpage","mail/mail.jsp");
			  mv.setViewName("index");
			return mv;
			
		}
		@RequestMapping("/mailproc")
		public ModelAndView mailproc(String title,String msg) {
			ModelAndView mv = new ModelAndView();
			service.mailsend(title,msg);
			 mv.addObject("mainpage","/home/main.jsp");
			 
				mv.setViewName("index");
			return mv;
			
		}
		@RequestMapping("/phsearch")
		public ModelAndView phsearch(@RequestParam("query") String query,@RequestParam("field") String field,
				Integer currentPage
				) {
		 
		    if(currentPage==null) {
				currentPage=1;
			}
			
			Pagelist pagelist=phonebookservice.search(field,query,currentPage);
			System.out.println(pagelist.getTotalPage());
			ModelAndView mv=new ModelAndView();
			mv.addObject("pagelist",pagelist);
			mv.addObject("mainpage","phonebook/pagelist.jsp");
			mv.setViewName("index");
			return mv;
		    
		    
		    
		  
		}
		
		@RequestMapping("/boardsearch")
		public ModelAndView boardsearch(@RequestParam("query") String query,@RequestParam("field") String field,
				Integer currentPage
				) {
		 
		    if(currentPage==null) {
				currentPage=1;
			}
			
		    BoardpageList BoardpageList=boardservice.search(field,query,currentPage);
			System.out.println(BoardpageList.getTotalPage());
			ModelAndView mv=new ModelAndView();
			mv.addObject("pagelist",BoardpageList);
			mv.addObject("mainpage","replyboard/pageList.jsp");
			mv.setViewName("index");
			return mv;
		    
		    
		    
		  
		}
		@RequestMapping("/star")
		public ModelAndView star() {
			ModelAndView md = new ModelAndView();
			StarVO star = Star.testWebScraping();
			md.addObject("star",star);
			md.addObject("mainpage","/star.jsp");
			md.setViewName("index");
			return md;
		}
		@RequestMapping("/com")
		public ModelAndView commit() {
			ModelAndView md = new ModelAndView();
			md.setViewName("/commit/index");
			return md;
		}
		@RequestMapping("/moa")
		public ModelAndView moa() {
			ModelAndView md = new ModelAndView();
			md.setViewName("/moa/moa");
			return md;
		}
		 @RequestMapping("/moalogin")
		 	public ModelAndView moalogin() {
			
			 ModelAndView mv=new ModelAndView();
			 mv.setViewName("/moa/moalogin");
			 return mv;
	}
		 @RequestMapping("/moaloginProc")
		 public ModelAndView moaloginproc(String id,String password,HttpSession session,HttpServletRequest request, HttpServletResponse response) {
			 ModelAndView mv=new ModelAndView();
			 System.out.println(id+password);
			if(databaseService.moaLoginChk(id, password)) {
				
				session.setAttribute("id", id);
				session.setAttribute("password", password);
				session.setAttribute("useid",databaseService.getUserIdByUsername(id) );
				mv.setViewName("/moa/moa");
				
		 }	
			else {
				mv.setViewName("/moa/logfail");
				
			}
			return mv;
		 }
		 @RequestMapping("/gmarket")
		 public ModelAndView gmarket(String store) {
			 ModelAndView mv = new ModelAndView();
			
			return null;
		 }
		 @RequestMapping(value = "/input", method = RequestMethod.POST)
		 public ModelAndView input(
		         @RequestParam("store") int siteId,
		         @RequestParam("url") String url,
		         @RequestParam("username") String username, 
		         RedirectAttributes redirectAttributes) {
		     ModelAndView mv = new ModelAndView();
		     switch (siteId) {
			case 1: String site = "쿠팡";
				
				break;
			case 2 :  site = "지마켓";
			
			break;
			case 3 : site = "11번가";
			default:
				break;
			}
		//     try {
		         int userId = databaseService.getUserIdByUsername(username);  
		         System.out.println(userId);
		         gogoVO result = gogo.scrapeData(siteId, url);
		         databaseService.saveToDatabase(result, userId,result.getPrice());  
		         System.out.println(result.toString());
		         System.out.println("일단성공");
		         redirectAttributes.addFlashAttribute("successMessage", "Data successfully scraped and saved!");
		         mv.setViewName("redirect:/moa");  
					/*
					 * } catch (Exception e) { System.out.println("실패");
					 * redirectAttributes.addFlashAttribute("errorMessage",
					 * "Error scraping or saving data. Please try again.");
					 * mv.setViewName("redirect:/moa"); }
					 */
		     return mv;
		 }



		       @RequestMapping("/moajoinmember")
				public ModelAndView moajoin() {
					ModelAndView mv=new ModelAndView();
					mv.setViewName("/moa/moajoinmember");
					return mv;
				}
		       
		       @RequestMapping("/moajoinproc")
				public ModelAndView moajoinproc(String id,String name,String password){ 
				UserVO VO = new UserVO(name,password);
					int result = databaseService.moaJoinMember(VO);
					ModelAndView mv=new ModelAndView();
					if(result>0) {
						mv.setViewName("/moa/moa");
					}
					else{
						mv.addObject("mainpage", "moa/memberfail.jsp");
						mv.setViewName("/moa/moa");
					}
					return mv;
				}
				@RequestMapping("/look")
				public ModelAndView look(String username,HttpServletRequest request, HttpServletResponse response){
					int userid = databaseService.getUserIdByUsername(username);
					List<Product> productList = databaseService.getProductsByUserId(userid);
					ModelAndView md = new ModelAndView();
					System.out.println(productList.toString());
					System.out.println(username);
					md.addObject("productList", productList);
					md.setViewName("/moa/moa");
					return md;

				}
				@RequestMapping(value = "/priceData", method = RequestMethod.GET, produces = "application/json")
				@ResponseBody
				public PriceData getPriceData(@RequestParam("itemId") String itemId) {
				    // itemId 값을 로깅합니다.
				    System.out.println("Received itemId: " + itemId);

				    // itemId를 사용하여 데이터베이스에서 가격 정보를 가져옵니다.
				    // 이 부분은 실제 구현 방법에 따라 다를 수 있습니다.
				    return null ; //databaseService.getPriceDataForItem(itemId);
				}

				}
		       
		    
		 
		




	
		
				
				
			
		

		
		
	
	



