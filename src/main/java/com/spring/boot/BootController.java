package com.spring.boot;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
// @ConfigurationProperties(prefix="amazon")
public class BootController {
	private BootRepository bootRepository;
	private AmazonProperties amazonProperties;
	// private String associateId;
	//
	// public void setAssociateId(String associateId) {
	// this.associateId = associateId;
	// }

	@Autowired
	public BootController(BootRepository bootRepository, AmazonProperties amazonPropertiess) {
		this.bootRepository = bootRepository;
		this.amazonProperties = amazonPropertiess;
	}

	@RequestMapping(value = "/{reader}", method = RequestMethod.GET)
	public String readersBooks(@PathVariable("reader") String reader, Model model) {
		List<Book> readingList = bootRepository.findByReader(reader);
		if (readingList != null) {
			model.addAttribute("books", readingList);
			model.addAttribute("reader", reader);
			model.addAttribute("amazonID", amazonProperties.getAssociateId());
		}
		return "readingList";
	}

	@RequestMapping(value = "/{reader}", method = RequestMethod.POST)
	public String addToReadingList(@PathVariable("reader") String reader, Book book) {
		book.setReader(reader);
		bootRepository.save(book);
		return "redirect:/{reader}";
	}
}
