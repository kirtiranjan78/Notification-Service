package com.mindfire.notificationService.util;

import java.io.InputStream;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;

/**
 * This class is responsible for processing the password reset email template
 * using JasperReports.
 * <p>
 * It loads a predefined Jasper report template, fills in dynamic values such as
 * the verification link and the user's first name, and then exports the
 * generated email content as an HTML string.
 * </p>
 */
@Component
public class PasswordResetTemplateProcessor {

	/**
	 * Generates an HTML email template for password reset using JasperReports.
	 * <p>
	 * This method loads the {@code PasswordResetEmailTemplate.jasper} file, fills
	 * it with dynamic parameters, and converts the result into an HTML string.
	 * </p>
	 *
	 * @param verificationLink the password reset verification link
	 * @param firstName        the first name of the user
	 * @return an HTML-formatted email template as a {@code String}
	 * @throws RuntimeException if an error occurs during template processing
	 */

	public String generateEmailHtml(String verificationLink, String firstName) {
		try {
			InputStream reportStream = new ClassPathResource("templates/PasswordResetEmailTemplate.jasper")
					.getInputStream();
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(reportStream);

			Map<String, Object> parameters = new HashMap<>();
			parameters.put("verificationLink", verificationLink);
			parameters.put("firstName", firstName);

			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
			StringWriter stringWriter = new StringWriter();
			HtmlExporter exporter = new HtmlExporter();
			exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			exporter.setExporterOutput(new SimpleHtmlExporterOutput(stringWriter));
			exporter.exportReport();

			return stringWriter.toString();
		} catch (Exception e) {
			throw new RuntimeException("Error generating  email template");
		}
	}

}
