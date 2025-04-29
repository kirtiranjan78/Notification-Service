package com.mindfire.notificationService.util;

public class HtmlEmailBodyUtility {
    public static String getTemplateBodyForAchievementMail() {
        String emailContent = """
                <html>
                <body style="font-family: Arial, sans-serif; background-color: #f4f4f4; padding: 20px; margin: 0;">
                    <div style="max-width: 600px; margin: auto; background: #ffffff; padding: 20px; border-radius: 10px; box-shadow: 0 4px 8px rgba(0,0,0,0.1);">
                        <h2 style="text-align: center; color: #4CAF50;">Congratulations!</h2>
                        <p>Dear <strong>%s</strong>,</p>
                        <p>
                            We are thrilled to inform you that you have been awarded a new certificate for your achievements and contributions.
                            Your dedication and hard work continue to inspire excellence across our organization.
                        </p>
                        <p style="text-align: center; margin: 30px 0;">
                            <a href="https://your-website.com/login" 
                               style="background-color: #4CAF50; color: white; padding: 12px 20px; text-decoration: none; border-radius: 5px; font-size: 16px;">
                                View Certificate
                            </a>
                        </p>
                        <p>
                            You can also log in to the website to view and download your certificate at your convenience.
                        </p>
                        <p>Keep up the great work!</p>
                        <br>
                        <p>Best regards,<br><strong>%s</strong></p>
                    </div>
                </body>
                </html>
                """;
        return emailContent;
    }
}
