package com.yuanmaxinxi.web.enroll;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.yuanmaxinxi.dto.ResultDTO;
import com.yuanmaxinxi.dto.enroll.EnrollQueryPageDTO;
import com.yuanmaxinxi.service.EnrollService;
@RequestMapping("/enroll")
@Controller
public class EnrollController{
	@Autowired
	private EnrollService enrollService;
//	@Autowired
//	private UniversityService universityService;
//	@Autowired
//	private MajorService majorService;
//	@Autowired
//	private ProvinceService provinceService;
	
	public static int rownum= 0;//导入excel的总数
	public static String msg="正在上传文件...";//导入excel的消息
	public static int currentNumber = 0;//加入内存中的数量
	public static int status = 0;//0--正在读  1-正在保存到内存数据库 2-操作完成
	@RequestMapping("/list")
	public String list(EnrollQueryPageDTO dto,Model model) {
		enrollService.queryPage(dto);
		model.addAttribute("dto",dto);
		return "enroll/list";
	}
	@RequestMapping("/showAdd")
	public String list(Model model) {
//		model.addAttribute("ulist", us.selectAll());
//		model.addAttribute("mlist", ms.selectAll());
//		model.addAttribute("pList", ps.selectAll());
		return "enroll/add";
	}
	/**
	 * 获取导入信息
	 * @param file
	 * @param req
	 * @return
	 */
	@RequestMapping("/getImortMsg")
	public @ResponseBody ResultDTO getImortMsg(MultipartFile file,HttpServletRequest req){
		Map<String,Object> map = new HashMap<>();
		map.put("rownum", rownum);
		map.put("msg", msg);
		map.put("currentNumber", currentNumber);
		map.put("status", status);
		return ResultDTO.newInstance(map);
	}
	@RequestMapping("/importEnroll")
	public @ResponseBody ResultDTO upload(MultipartFile file,HttpServletRequest req){
		ResultDTO dto;
		try {
	        Sheet sheet = null;
	        Row row = null;
	        String cellData = null;
	        String columns[] = {"province","universityName","time","major","no","pNo","专业名称","batch","maxNumber","minNumber","avgNumber","number","minRanking"};
	        String fileName = file.getOriginalFilename();
	        InputStream in = file.getInputStream();
	        Workbook wb = readExcel(fileName,in);
	        msg = "文件上传成功!开始保存数据";
	        if(wb != null){
	            //获取第一个sheet
	            sheet = wb.getSheetAt(0);
	            //获取最大行数
	            rownum = sheet.getPhysicalNumberOfRows();
	            //获取第一行
	            row = sheet.getRow(0);
	            //获取最大列数
	            int colnum = row.getPhysicalNumberOfCells();
	            for (int i = 1; i<rownum; i++) {
	                Map<String,String> map = new LinkedHashMap<String,String>();
	                row = sheet.getRow(i);
	                if(row !=null){
	                    for (int j=0;j<colnum;j++){
	                        cellData = (String) getCellFormatValue(row.getCell(j));
	                        map.put(columns[j], cellData);
	                    }
	                }else{
	                    break;
	                }
	               msg = "正在保存第["+i+"/"+rownum+"]条数据";
	               status = 1;
	               currentNumber += enrollService.importEnroll(map);
	            }
	        }
	        msg = "数据保存完成.";
	        status = 2;
			dto = ResultDTO.newInstance(true,"导入成功!");
		} catch (Exception e) {
			e.printStackTrace();
			dto = ResultDTO.newInstance(false,"导入失败,请稍后再试.");
		}
		return dto;
	}
	//读取excel
    private Workbook readExcel(String fileName,InputStream is){
        Workbook wb = null;
        String extString = fileName.substring(fileName.lastIndexOf("."));
        try {
            if(".xls".equals(extString)){
                return wb = new HSSFWorkbook(is);
            }else if(".xlsx".equals(extString)){
                return wb = new XSSFWorkbook(is);
            }else{
                return wb = null;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wb;
    }
    private Object getCellFormatValue(Cell cell){
        Object cellValue = null;
        if(cell!=null){
            //判断cell类型
            switch(cell.getCellType()){
            case Cell.CELL_TYPE_NUMERIC:{
                cellValue = String.valueOf(cell.getNumericCellValue());
                break;
            }
            case Cell.CELL_TYPE_FORMULA:{
                //判断cell是否为日期格式
                if(DateUtil.isCellDateFormatted(cell)){
                    //转换为日期格式YYYY-mm-dd
                    cellValue = cell.getDateCellValue();
                }else{
                    //数字
                    cellValue = String.valueOf(cell.getNumericCellValue());
                }
                break;
            }
            case Cell.CELL_TYPE_STRING:{
                cellValue = cell.getRichStringCellValue().getString();
                break;
            }
            default:
                cellValue = "";
            }
        }else{
            cellValue = "";
        }
        return cellValue;
    }
//	/*
//	 * 从页面获取值并封装为Enroll对象
//	 */
//	private Enroll getEnroll(HttpServletRequest req) throws NumberFormatException {
//		Enroll e = new Enroll();
//		
//		String id = req.getParameter("id");
//		if(StringUtil.isNotNullAndEmpty(id)) {
//			e.setId(Long.parseLong(id));
//		}
//		e.setuId(Long.parseLong(req.getParameter("uId")));
//		e.setmId(Long.parseLong(req.getParameter("mId")));
//		//e.setBatch(req.getParameter("batch"));
//		
//		e.setNumber(Integer.parseInt(req.getParameter("number")));
//		String minNumber=req.getParameter("minNumber");
//		e.setMinNumber(StringUtil.isNullOrEmpty(minNumber)?0:Integer.parseInt(minNumber));
//		String maxNumber=req.getParameter("maxNumber");
//		e.setMaxNumber(StringUtil.isNullOrEmpty(maxNumber)?0:Integer.parseInt(maxNumber));
//		String avgNumber=req.getParameter("avgNumber");
//		e.setAvgNumber(StringUtil.isNullOrEmpty(avgNumber)?0:Integer.parseInt(avgNumber));
//		String minRanking=req.getParameter("minRanking");
//		e.setMinRanking(StringUtil.isNullOrEmpty(minRanking)?0:Integer.parseInt(minRanking));
//		String maxRanking=req.getParameter("maxRanking");
//		e.setMaxRanking(StringUtil.isNullOrEmpty(maxRanking)?0:Integer.parseInt(maxRanking));
//		String avgRanking=req.getParameter("avgRanking");
//		e.setAvgRanking(StringUtil.isNullOrEmpty(avgRanking)?0:Integer.parseInt(avgRanking));
//		e.setTime(0);
//		e.setTuition(new BigDecimal(req.getParameter("tuition")));
//		e.setStudyYear(new BigDecimal(req.getParameter("studyYear")));
//		return e;
//	}
//	
//	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		String cmd = req.getParameter("cmd");
//		System.err.println(cmd);
//		if ("showAdd".equals(cmd)) {
//			
//		}else if("add".equals(cmd)) {
//			ResultDTO dto;
//			try {
//				es.insert(getEnroll(req));
//				dto = ResultDTO.newInstance(true, "添加成功!");
//			} catch (Exception e) {
//				e.printStackTrace();
//				dto = ResultDTO.newInstance(false, e.getMessage());
//			}
//			putJson(dto, resp);
//		}else if("showEdit".equals(cmd)) {
//			String idStr = req.getParameter("id");
//			if (StringUtil.isNotNullAndEmpty(idStr)) {
//				req.setAttribute("obj", es.selectOneById(Long.parseLong(idStr)));
//				req.setAttribute("ulist", us.selectAll());
//				req.setAttribute("mlist", ms.selectAll());
//				req.setAttribute("pList", ps.selectAll());
//				req.getRequestDispatcher("/WEB-INF/enroll/edit.jsp").forward(req, resp);
//			}else {
//				resp.sendRedirect("/enroll");
//			}
//		}else if("edit".equals(cmd)) {
//			ResultDTO dto;
//			try {
//				es.insert(getEnroll(req));
//				dto = ResultDTO.newInstance(true, "添加成功!");
//			} catch (Exception e) {
//				e.printStackTrace();
//				dto = ResultDTO.newInstance(false, e.getMessage());
//			}
//			putJson(dto, resp);
//		}else if("delete".equals(cmd)) {
//			ResultDTO dto;
//			try {
//				es.delete(Long.parseLong(req.getParameter("id")));
//				dto=ResultDTO.newInstance(true, "删除成功");
//			}catch (Exception e) {
//				dto=ResultDTO.newInstance(false, e.getMessage());
//			}
//			putJson(dto, resp);
//		}
//	}
}