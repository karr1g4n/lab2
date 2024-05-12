<?xml version="1.0"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" indent="yes"/>

    <xsl:template match="/">
        <html>
            <body>
                <h2>Business Meetings</h2>
                <table border="1" width="100%">
                    <tr bgcolor="#9acd32">
                        <th>Date and Time</th>
                        <th>Attendee</th>
                        <th>Location</th>
                    </tr>
                    <xsl:apply-templates select="node()"/>
                </table>
            </body>
        </html>
    </xsl:template>

    <!-- Ваш шаблон залишається практично незмінним -->
    <xsl:template match="Meeting">
        <tr>
            <td><xsl:value-of select="DateTime"/></td>
            <td><xsl:value-of select="Attendee"/></td>
            <td><xsl:value-of select="Location"/></td>
        </tr>
    </xsl:template>
</xsl:stylesheet>
