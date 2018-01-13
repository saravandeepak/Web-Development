package com.huskyoneapp.pdfview;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.huskyoneapp.dao.HousingListingDAO;
import com.huskyoneapp.dao.UserAccountDAO;
import com.huskyoneapp.entity.HousingListing;
import com.huskyoneapp.repository.HousingListingRepository;
import com.huskyoneapp.repository.UserAccountRepository;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class PDFHousingView extends AbstractITextPdfView{
	
	@Autowired 
	private HousingListingRepository housingListingRepository;
	
	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<HousingListing> housingListings =  (List<HousingListing>) housingListingRepository.findAll();
		document.add(new Paragraph("Housing Listing report"));
		PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100.0f);
        table.setWidths(new float[] {3.0f, 2.0f, 2.0f, 2.0f, 1.0f});
        table.setSpacingBefore(10);
		// TODO Auto-generated method stub
        
        PdfPCell cell = new PdfPCell();
        
        cell.setPhrase(new Phrase("Listing Id"));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Title"));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Type"));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Status"));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Rent"));
        table.addCell(cell);
        
        for (HousingListing house: housingListings){
        	table.addCell(String.valueOf(house.getHousingListingId()));
            table.addCell(house.getTitle());
            table.addCell(house.getType());
            table.addCell(house.getApproved());
            table.addCell(String.valueOf(house.getRent()));
        }
        document.add(table);
	}

}
