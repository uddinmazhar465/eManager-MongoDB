package com.mzr.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mzr.dto.EmployeeDTO;
import com.mzr.dto.ProjectDTO;
import com.mzr.model.Employee;
import com.mzr.qrcode.QRCodeUtils;
import com.mzr.service.EmployeeService;
import com.mzr.validators.EmpValidator;

@Controller
public class EmployeeController {
	@Autowired
	private EmployeeService service;

	@ModelAttribute("gender")
	public List<String> genderModel() {
		return List.of("Male", "Female", "Other");
	}

	@ModelAttribute("qualification")
	public List<String> qualificationModel() {
		return List.of("BCA", "BTech", "BSc", "MCA", "MTech", "MSc");
	}

	@ModelAttribute("pLanguage")
	public List<String> pLanguageModel() {
		return List.of("Java", "Python", ".NET", "Php", "UI", "Android", "SQL", "MongoDB");
	}

	@GetMapping("/home.htm")
	public String home() {
		return "home";
	}

	@GetMapping("/insert.htm")
	public String setForm(@ModelAttribute("emp") EmployeeDTO dto) {
		dto.setGender(genderModel().get(0));
		dto.setQualification(List.of(qualificationModel().get(0)));
		dto.setPlanguage(List.of(pLanguageModel().get(0)));
		return "emp-register";
	}

	@PostMapping("/insert.htm")
	public String registerEmp(@ModelAttribute("emp") EmployeeDTO eDto, @RequestParam("f") MultipartFile file,
			RedirectAttributes redirect, HttpSession session, BindingResult bind) throws IOException {
		EmpValidator valid = new EmpValidator();
		valid.validate(eDto, bind);
		if (bind.hasErrors()) {
			return "emp-register";
		} else {
			eDto.setImage(new Binary(BsonBinarySubType.BINARY, file.getBytes()));
			session.setAttribute("eSession", eDto);
			redirect.addFlashAttribute("count", eDto.getProjCount());
			return "redirect:project.htm";
		}
	}

	@GetMapping("/project.htm")
	public String projectForm(@ModelAttribute("proj") ProjectDTO pDTO) {
		return "emp-register-p";
	}

	@PostMapping("/project.htm")
	public String projectSet(Map<String, Object> map, @ModelAttribute("proj") ProjectDTO pDTO, HttpSession session,
			BindingResult bind) {

		EmployeeDTO eDTO2 = (EmployeeDTO) session.getAttribute("eSession");
		eDTO2.setId(service.generateSequence(Employee.SEQUENCE_NAME));
		String[] projNo = pDTO.getProjno().split(",");
		String[] projName = pDTO.getProjName().split(",");
		String[] lang = pDTO.getLang().split(",");
		String[] database = pDTO.getDatabase().split(",");
		String[] teamSize = pDTO.getTeamSize().split(",");
		List<ProjectDTO> projList = new ArrayList<>();
		ProjectDTO plocal = null;
		for (int i = 0; i < projNo.length; i++) {
			plocal = new ProjectDTO();

			plocal.setProjno(projNo[i]);
			plocal.setProjName(projName[i]);
			plocal.setLang(lang[i]);
			plocal.setDatabase(database[i]);
			plocal.setTeamSize(teamSize[i]);
			projList.add(plocal);
		}

		eDTO2.setProject(projList);

		String text = "ID=" + eDTO2.getId() + "\nName=" + eDTO2.getName() + "\nGender=" + eDTO2.getGender()
				+ "\nAddress=" + eDTO2.getAddress() + "\nEmail=" + eDTO2.getEmail() + "\nPhone=" + eDTO2.getPhone()
				+ "\nPassword=" + eDTO2.getPassword() + "\nQualification=" + eDTO2.getQualification()
				+ "\nProgLanguage=" + eDTO2.getPlanguage() + "\nProject=" + eDTO2.getProject();

		eDTO2.setQrcode(QRCodeUtils.getQRCodeImage(text, 400, 400));
		map.put("empShow", eDTO2);
		map.put("msg", service.insert(eDTO2, "insert"));
		return "emp-save";
	}

	@GetMapping("/show.htm")
	public String getAllEmp(Map<String, Object> map) {
		List<Employee> list = service.getAll();
		list.forEach(e -> {
			Binary b = e.getImage();
			String img = Base64.getEncoder().encodeToString(b.getData());
			e.setImageStr("data:image/jpg;base64," + img);

			String qrText = "ID=" + e.getId() + "\nName=" + e.getName() + "\nGender=" + e.getGender() + "\nAddress="
					+ e.getAddress() + "\nEmail=" + e.getEmail() + "\nPhone=" + e.getPhone() + "\nPassword="
					+ e.getPassword() + "\nQualification=" + e.getQualification() + "\nProgLanguage=" + e.getPlanguage()
					+ "\nProject=" + e.getProject();

			e.setQrcode(QRCodeUtils.getQRCodeImage(qrText, 100, 100));
		});
		map.put("emp", list);

		return "emp-ShowAll";
	}

	@GetMapping("/show-in-detail.htm")
	public String showById(Map<String, Object> map, @RequestParam("id") Integer id) {
		Employee e = service.getById(id);
		Binary b = e.getImage();
		String img = Base64.getEncoder().encodeToString(b.getData());
		e.setImageStr("data:image/jpg;base64," + img);
		String qrText = "ID=" + e.getId() + "\nName=" + e.getName() + "\nGender=" + e.getGender() + "\nAddress="
				+ e.getAddress() + "\nEmail=" + e.getEmail() + "\nPhone=" + e.getPhone() + "\nPassword="
				+ e.getPassword() + "\nQualification=" + e.getQualification() + "\nProgLanguage=" + e.getPlanguage()
				+ "\nProject=" + e.getProject();

		e.setQrcode(QRCodeUtils.getQRCodeImage(qrText, 500, 500));
		map.put("showEmpById", e);
		return "emp-showById";
	}

	@GetMapping("/edit.htm")
	public String showAllForEdit(RedirectAttributes redirect) {
		redirect.addFlashAttribute("eMsg", "edit");
		return "redirect:show.htm";
	}

	@GetMapping("/delete.htm")
	public String deleteEmpById(RedirectAttributes redirect, @RequestParam("id") Integer id) {
		redirect.addFlashAttribute("eMsg", "edit");
		redirect.addFlashAttribute("dMsg", service.deleteById(id));
		return "redirect:edit.htm";
	}

	@GetMapping("/update.htm")
	public String showByIdInForm(Map<String, Object> map, @ModelAttribute("emp") EmployeeDTO eDTO,
			@RequestParam("id") Integer id, HttpSession session) {
		Employee e = service.getById(id);
		BeanUtils.copyProperties(e, eDTO);
		eDTO.setProjCount(e.getProject().size());
		List<ProjectDTO> plist = new ArrayList<ProjectDTO>();
		e.getProject().forEach(p -> {
			ProjectDTO pDto = new ProjectDTO();
			pDto.setProjno(p.getProjno().toString());
			pDto.setProjName(p.getProjName());
			pDto.setLang(p.getLang());
			pDto.setDatabase(p.getDatabase());
			pDto.setTeamSize(p.getTeamSize().toString());
			plist.add(pDto);
		});
		eDTO.setProject(plist);
		Binary b = e.getImage();
		String img = Base64.getEncoder().encodeToString(b.getData());
		eDTO.setImageStr("data:image/jpg;base64," + img);
		map.put("uMsg", "abc");
		map.put("imageStr", eDTO.getImageStr());
		map.put("photo", eDTO.getImage());
		session.setAttribute("eDTOupdate", eDTO);
		return "emp-register";
	}

	@PostMapping("/update.htm")
	public String updateEmpById(@RequestParam("f") MultipartFile file, RedirectAttributes redirect,
			@ModelAttribute("emp") EmployeeDTO empDTO, HttpSession session, BindingResult bind, Map<String, Object> map)
			throws IOException {

		EmployeeDTO eDTO = (EmployeeDTO) session.getAttribute("eDTOupdate");

		if (!(file.getOriginalFilename().isEmpty())) {
			empDTO.setImage(new Binary(BsonBinarySubType.BINARY, file.getBytes()));
		} else {
			EmployeeDTO dto2 = (EmployeeDTO) session.getAttribute("eDTOupdate");
			empDTO.setImage(dto2.getImage());
		}

		EmpValidator valid = new EmpValidator();
		valid.validate(empDTO, bind);
		if (bind.hasErrors()) {
			Binary b = eDTO.getImage();
			String img = Base64.getEncoder().encodeToString(b.getData());
			empDTO.setImageStr("data:image/jpg;base64," + img);
			map.put("imageStr", eDTO.getImageStr());
			map.put("uMsg", "abc");
			BeanUtils.copyProperties(eDTO, empDTO);
			return "emp-register";
		} else {
			session.setAttribute("empSes", empDTO);
			redirect.addFlashAttribute("count", empDTO.getProjCount());
			redirect.addFlashAttribute("eDTOproj", eDTO.getProject());
			redirect.addFlashAttribute("projExist", "y");
			return "redirect:projectupdate.htm";
		}
	}

	@GetMapping("/projectupdate.htm")
	public String setProjectForUpdate(@ModelAttribute("proj") ProjectDTO pDTO) {
		return "emp-register-p";
	}

	@PostMapping("/projectupdate.htm")
	public String updateProject(Map<String, Object> map, @ModelAttribute("proj") ProjectDTO pDTO, HttpSession session) {
		EmployeeDTO eDTO2 = (EmployeeDTO) session.getAttribute("empSes");

		String[] projNo = pDTO.getProjno().split(",");
		String[] projName = pDTO.getProjName().split(",");
		String[] lang = pDTO.getLang().split(",");
		String[] database = pDTO.getDatabase().split(",");
		String[] teamSize = pDTO.getTeamSize().split(",");
		List<ProjectDTO> projList = new ArrayList<>();
		ProjectDTO plocal = null;
		for (int i = 0; i < projNo.length; i++) {
			plocal = new ProjectDTO();
			;
			plocal.setProjno(projNo[i]);
			plocal.setProjName(projName[i]);
			plocal.setLang(lang[i]);
			plocal.setDatabase(database[i]);
			plocal.setTeamSize(teamSize[i]);
			projList.add(plocal);
		}
		eDTO2.setProject(projList);
		String text = "ID=" + eDTO2.getId() + "\nName=" + eDTO2.getName() + "\nGender=" + eDTO2.getGender()
				+ "\nAddress=" + eDTO2.getAddress() + "\nEmail=" + eDTO2.getEmail() + "\nPhone=" + eDTO2.getPhone()
				+ "\nPassword=" + eDTO2.getPassword() + "\nQualification=" + eDTO2.getQualification()
				+ "\nProgLanguage=" + eDTO2.getPlanguage() + "\nProject=" + eDTO2.getProject();

		eDTO2.setQrcode(QRCodeUtils.getQRCodeImage(text, 400, 400));
		map.put("empShow", eDTO2);
		map.put("msg", service.insert(eDTO2, "update"));
		return "emp-save";
	}

}
