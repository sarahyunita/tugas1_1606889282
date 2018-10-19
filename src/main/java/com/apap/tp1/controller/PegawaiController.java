package com.apap.tp1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.apap.tp1.model.PegawaiModel;
import com.apap.tp1.service.JabatanService;
import com.apap.tp1.service.PegawaiService;

@Controller
public class PegawaiController {
	@Autowired
	private PegawaiService pegawaiService;
	
	//@Autowired
	//private ProvinsiService provinsiService;
	
	@Autowired
	private JabatanService jabatanService;
	
	//@Autowired
	//private InstansiService instansiService;
	
	@RequestMapping("/")
	private String home(Model model) {
		
		model.addAttribute("listJabatan", jabatanService.getListJabatan());
		//model.addAttribute("listInstansi", instansiService.getAll());
		return "home";
	}
	
	@RequestMapping(value = "/pegawai", method = RequestMethod.GET)
	private String viewPegawai (@RequestParam(value = "nip") String nip, Model model) {
		PegawaiModel pegawai = pegawaiService.getPegawaiDetailByNip(nip);
		
		model.addAttribute("nip", nip);
		model.addAttribute("pegawai", pegawai);
		model.addAttribute("listJabatan", pegawai.getJabatanList());
		model.addAttribute("gaji", pegawaiService.countGaji(pegawai));
		return "view-pegawai";
	}
	
	
	
}
