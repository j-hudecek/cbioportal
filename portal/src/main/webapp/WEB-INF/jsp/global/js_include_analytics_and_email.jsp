<%--
 - Copyright (c) 2015 Memorial Sloan-Kettering Cancer Center.
 -
 - This library is distributed in the hope that it will be useful, but WITHOUT
 - ANY WARRANTY, WITHOUT EVEN THE IMPLIED WARRANTY OF MERCHANTABILITY OR FITNESS
 - FOR A PARTICULAR PURPOSE. The software and documentation provided hereunder
 - is on an "as is" basis, and Memorial Sloan-Kettering Cancer Center has no
 - obligations to provide maintenance, support, updates, enhancements or
 - modifications. In no event shall Memorial Sloan-Kettering Cancer Center be
 - liable to any party for direct, indirect, special, incidental or
 - consequential damages, including lost profits, arising out of the use of this
 - software and its documentation, even if Memorial Sloan-Kettering Cancer
 - Center has been advised of the possibility of such damage.
 --%>

<%--
 - This file is part of cBioPortal.
 -
 - cBioPortal is free software: you can redistribute it and/or modify
 - it under the terms of the GNU Affero General Public License as
 - published by the Free Software Foundation, either version 3 of the
 - License.
 -
 - This program is distributed in the hope that it will be useful,
 - but WITHOUT ANY WARRANTY; without even the implied warranty of
 - MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 - GNU Affero General Public License for more details.
 -
 - You should have received a copy of the GNU Affero General Public License
 - along with this program.  If not, see <http://www.gnu.org/licenses/>.
--%>

<%@ page import="org.mskcc.cbio.portal.util.GlobalProperties" %>

<%
String googleAnalyticsProfileId = GlobalProperties.getGoogleAnalyticsProfileId();
if (googleAnalyticsProfileId!=null && !googleAnalyticsProfileId.isEmpty()) {
%>
<!-- Google Analytics -->
<script type="text/javascript">

  var _gaq = _gaq || [];
  _gaq.push(['_setAccount', '<%=googleAnalyticsProfileId%>']);
  _gaq.push(['_trackPageview']);

  (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
  })();
</script>
<% } %>

<%
String piwikSiteId = GlobalProperties.getPiwikSiteId();
String piwikServerUrl = GlobalProperties.getPiwikServerUrl();
if (piwikSiteId!=null && !piwikSiteId.isEmpty() && piwikServerUrl!=null && !piwikServerUrl.isEmpty()) {
    org.springframework.security.core.Authentication auth = org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication();
    String username = "";
    if (auth != null)
        username =((org.cbioportal.security.spring.authentication.PortalUserDetails)auth.getPrincipal()).getName(); 
%>
<!-- Piwik open source tracking -->
<script type="text/javascript">
  var _paq = _paq || [];
  var username = '<%=username%>';
  if (username != '') {
    _paq.push(['setUserId', username])
  }
  _paq.push(['trackPageView']);
  _paq.push(['enableLinkTracking']);
  //cbioportal URLs can get too long for GET
  _paq.push(['setRequestMethod', 'POST']);
  (function() {
    var u="//<%=piwikServerUrl%>/";
    _paq.push(['setTrackerUrl', u+'piwik.php']);
    _paq.push(['setSiteId', '<%=piwikSiteId%>']);
    var d=document, g=d.createElement('script'), s=d.getElementsByTagName('script')[0];
    g.type='text/javascript'; g.async=true; g.defer=true; g.src=u+'piwik.js'; s.parentNode.insertBefore(g,s);
  })();
</script>
<!-- End Piwik Code -->
<% } %>

<!-- De-obfuscate All Email Addresses -->
<script type="text/javascript">
    <!-- When the document is ready, de-obfuscate the email addresses -->
    $(document).ready(function() {
        $('span.mailme').mailme();
    });
</script>
