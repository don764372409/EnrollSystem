package com.yuanmaxinxi.smallsoft.university;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.tribes.util.Arrays;

import com.yuanmaxinxi.dto.ResultDTO;
import com.yuanmaxinxi.dto.universitydto.UniversityQueryPageDTO;
import com.yuanmaxinxi.entity.major.Major2;
import com.yuanmaxinxi.entity.university.University;
import com.yuanmaxinxi.service.UniversityService;
import com.yuanmaxinxi.service.UserService;
import com.yuanmaxinxi.util.StringUtil;
import com.yuanmaxinxi.web.BaseServlet;

@WebServlet("/soft/university")
public class UniversityServlet extends BaseServlet{
	private static final long serialVersionUID = 1L;
	private UniversityService universityService;
	private UserService userService;
	private ResultDTO dto;
	private static String wheresql = "";
	@Override
	public void init() throws ServletException {
		universityService = new UniversityService();
	}
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String cmd = req.getParameter("cmd");
		//搜索查询
		if ("search".equals(cmd)) {
			try {
				String name = req.getParameter("name");
				System.out.println(name);
				if(StringUtil.isNotNullAndEmpty(name)) {
					wheresql= " instr(name,\""+name+"\")";
				}
				resp.sendRedirect("/soft/university");
			} catch (Exception e) {
				e.printStackTrace();
				dto = ResultDTO.newInstance(false, e.getMessage());
			}
			putJson(dto, resp);
		}else if("selectUnisByIds".equals(cmd)) {
			String ids = req.getParameter("ids");
			List<University> list = universityService.selectUnisByIds(ids);
			putJson(list, resp);
		}
		else if("getSelectUnis".equals(cmd)) {
			String[] ids = req.getParameterValues("ids");
			List<University> list = universityService.getSelectUnis(ids);
			putJson(list, resp);
		}
		else if("addShoucang".equals(cmd)) {
			try {
				String id = req.getParameter("id");
				String uId = req.getParameter("uId");
				universityService.addShoucang(uId,id);
				dto = ResultDTO.newInstance(true, "收藏成功!");
			} catch (Exception e) {
				dto = ResultDTO.newInstance(false, e.getMessage());
			}
			putJson(dto, resp);
		}else if("selectShoucangUnis".equals(cmd)) {
			String uId = req.getParameter("uId");
			List<University> unis = universityService.selectShoucangUnis(uId);
			putJson(unis, resp);
		}
		else if("unShoucang".equals(cmd)) {
			try {
				String id = req.getParameter("id");
				String uId = req.getParameter("uId");
				universityService.unShoucang(uId,id);
				dto = ResultDTO.newInstance(true, "取消收藏成功!");
			} catch (Exception e) {
				dto = ResultDTO.newInstance(true, "取消收藏失败!"+e.getMessage());
			}
			putJson(dto, resp);
		}else if("shoucang".equals(cmd)) {
			String id = req.getParameter("id");
			String uId = req.getParameter("uId");
			University uni = universityService.selectOneShoucang(uId,id);
			putJson(uni, resp);
		}else if("luquYear".equals(cmd)) {
			String id = req.getParameter("id");
			String mId = req.getParameter("mId");
			String activBatch = req.getParameter("activBatch");
			List<Map<String,Object>> majors = universityService.selectYearByMajorAndBidAndId(id,activBatch,mId);
			putJson(majors, resp);
		}else if("luquMajors".equals(cmd)) {
			String id = req.getParameter("id");
			String activBatch = req.getParameter("activBatch");
			List<Major2> majors = universityService.selectMajorsById(id,activBatch);
			putJson(majors, resp);
		}
		else if("details1".equals(cmd)){
			try {
				String idStr = req.getParameter("id");
				long id = Long.parseLong(idStr);
				University uni = universityService.selectOneById(id);//查询t_university表
				List jianzhangs = universityService.selectOneByuId(id);
				uni.setList(jianzhangs);
				//院校简介数据处理
				String remark = uni.getRemark();
				String replace = remark.replace(" 　　", "\n&emsp;&emsp;");
				uni.setRemark(replace);
				//院校师资力量数据处理
				String teachers = uni.getTeachers();
				if(teachers.contains("下面")) {
					int indexOf = teachers.indexOf("下面");
					teachers=teachers.substring(0, indexOf);
					uni.setTeachers(teachers);
				}
				putJson(uni, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			//接受请求参数
			UniversityQueryPageDTO dto = new UniversityQueryPageDTO();
			//分页参数
			String str1 = req.getParameter("currentPage");
			int currentPage = Integer.parseInt(StringUtil.isNotNullAndEmpty(str1)?str1:"1");
			String str2 = req.getParameter("pageSize");
			int pageSize = Integer.parseInt(StringUtil.isNotNullAndEmpty(str2)?str2:"10");
			dto.setCurrentPage(currentPage);
			dto.setPageSize(pageSize);
			//高级查询参数
			String name = req.getParameter("name");
			dto.setName(name);
			String record = req.getParameter("record");
			dto.setRecord(record);
			String property = req.getParameter("property");
			dto.setProperty(property);
			String pIdStr = req.getParameter("pId");
			Long pId = 0L;
			try {
				pId = Long.parseLong(pIdStr);
			} catch (Exception e) {
				System.err.println("转换省外键失败");
			}
			dto.setpId(pId);
			String f211Str = req.getParameter("f211");
			int f211 = -1;
			try {
				f211 = Integer.parseInt(f211Str);
			} catch (Exception e) {
				System.err.println("转换学校类型失败");
			}
			dto.setF211(f211);
			String f985Str = req.getParameter("f985");
			int f985 = -1;
			try {
				f985 = Integer.parseInt(f985Str);
			} catch (Exception e) {
				System.err.println("转换学校类型失败");
			}
			dto.setF985(f985);
			String ranking1Str = req.getParameter("ranking1");
			int ranking1 = -1;
			try {
				ranking1 = Integer.parseInt(ranking1Str);
			} catch (Exception e) {
				System.err.println("转换排名失败");
			}
			dto.setRanking1(ranking1);
			String ranking2Str = req.getParameter("ranking2");
			int ranking2 = -1;
			try {
				ranking2 = Integer.parseInt(ranking2Str);
			} catch (Exception e) {
				System.err.println("转换排名失败");
			}
			dto.setRanking2(ranking2);
			//调用方法处理请求
			//queryPage
			//响应
			universityService.queryPage(dto);//0-10条数据
			putJson(dto, resp);
		}
	}
}
