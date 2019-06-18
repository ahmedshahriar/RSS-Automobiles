package eventHandling;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Date;

import javax.swing.JFrame;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;

import guiPackage.OrderForm;

public class ExportPdf  implements ActionListener {
    public static ExportPdf exportPdfRef=null;
    Rectangle r=new Rectangle(PageSize.LETTER);
    public Document doc = new Document();

    public ExportPdf(){
        System.out.println("exportPDF");
        doc.setPageSize(r);
    }

    @Override
    public void actionPerformed(ActionEvent a) {
        int i= OrderFormEvent.getPrevOrerId();
        OrderForm orderFormRef =   OrderForm.getorderformRef();;

        if(a.getSource()==orderFormRef.creatbill)
            doc.open();
            try {
                PdfWriter.getInstance(doc, new FileOutputStream("OrderForm"+i+".pdf"));
                doc.open();
                System.out.println(doc+" doc open");

                //ShowroomName
                Paragraph showroomName = new Paragraph("RSS Automobiles", FontFactory.getFont(FontFactory.COURIER_BOLD, 30, Font.BOLD, BaseColor.BLACK));
                showroomName.setAlignment(Element.ALIGN_CENTER);
                doc.add(showroomName);
                //Logo
                Image img = Image.getInstance("Banner.jpg");
                img.scaleAbsolute(500, 100);
                img.setAlignment(Element.ALIGN_CENTER);

                doc.add(img);
                doc.add(new Paragraph(" "));


                Chunk setHead = new Chunk(new VerticalPositionMark());
                Paragraph dateTitle = new Paragraph("Date : ");
                //Paragraph p = new Paragraph(new Date().toString());
                Paragraph p = new Paragraph("Date : ");
                p.add(new Date().toString());
                p.add(new Chunk(setHead));
                p.add("Order NO : ");
                p.add(new Paragraph(orderFormRef.setOrderID.getText().toString()));
                doc.add(p);

                doc.add(new Paragraph(" "));

                PdfPTable table = new PdfPTable(4);
                PdfPCell cell1 = new PdfPCell(new Paragraph("Customer Info"));
                cell1.setColspan(2);
                cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);


                PdfPCell cell2 = new PdfPCell(new Paragraph("Product Info"));
                cell2.setColspan(2);
                cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell2.setBackgroundColor(BaseColor.LIGHT_GRAY);

                table.addCell(cell1);
                table.addCell(cell2);
                table.addCell("Name");
                table.addCell(new Paragraph(orderFormRef.txtname.getText().toString()));
                table.addCell("Car ID");
                table.addCell(new Paragraph(orderFormRef.selectCarID.getSelectedItem().toString()));

                table.addCell("Address");
                table.addCell(new Paragraph(orderFormRef.txtaddress.getText().toString()));
                table.addCell("Model");
                table.addCell(new Paragraph(orderFormRef.txtmodel.getText().toString()));

                table.addCell("Phone");
                table.addCell(new Paragraph(orderFormRef.txtphone.getText().toString()));
                table.addCell("Manufacturer");
                table.addCell(new Paragraph(orderFormRef.txtman.getText().toString()));

                table.addCell("Mail");
                table.addCell(new Paragraph(orderFormRef.txtmail.getText().toString()));
                table.addCell("Price(BDT)");
                table.addCell(new Paragraph(orderFormRef.txtprice.getText().toString()));

                PdfPCell cellnoBorder1 = new PdfPCell();
                cellnoBorder1.setBorder(PdfPCell.NO_BORDER);
                table.addCell(cellnoBorder1);

                PdfPCell cellnoBorder2 = new PdfPCell();
                cellnoBorder2.setBorder(PdfPCell.NO_BORDER);
                table.addCell(cellnoBorder2);

                table.addCell("Warranty(Year)");
                table.addCell(new Paragraph(orderFormRef.txtwarranty.getText().toString()));

                doc.add(table);

                doc.add(new Paragraph(" "));

                PdfPTable table2 = new PdfPTable(2);

                table2.addCell("VAT (%) ");
                table2.addCell(new Paragraph(orderFormRef.txtvat.getText().toString()));

                table2.addCell("Discount");
                table2.addCell(new Paragraph(orderFormRef.txtdis.getText().toString()));

                table2.addCell("Total Price(BDT)");
                table2.addCell(new Paragraph(orderFormRef.displayPrice.getText().toString(), FontFactory.getFont(FontFactory.COURIER_BOLD, 20, Font.BOLD, BaseColor.BLACK)));

                doc.add(table2);
                doc.add(new Paragraph(" "));
                doc.add(new Paragraph(" "));
                doc.add(new Paragraph(" "));
                doc.add(new Paragraph("-------------------------------------------------------------------------------------------------------------------------------------- "));
                doc.add(new Paragraph(" "));
                doc.add(new Paragraph(" "));
                doc.add(new Paragraph(" "));
                doc.add(new Paragraph(" "));
                //Signature
                Chunk setSignAuthority = new Chunk(new VerticalPositionMark());
                Paragraph sign = new Paragraph("Authority Signature ");
                sign.add(new Chunk(setSignAuthority));
                sign.add(new Paragraph("Customer Signature  "));
                doc.add(sign);
                System.out.println("pdf done");

                doc.close();
                System.out.println(doc+" doc close");
            } catch (Exception e) {
                //doc.open();
                // TODO Auto-generated catch block
                //e.printStackTrace();
                System.out.println("!!!");
            }
    }
    public  static ExportPdf getExportPdfRef(){
        if(exportPdfRef==null){
            exportPdfRef = new ExportPdf();
        }
        return exportPdfRef;
    }
}
