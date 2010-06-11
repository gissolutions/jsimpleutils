package org.gissolutions.jsimpleutils.net;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PingTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testPing() {
		Ping p = new Ping(false);
		String host="faii-wks-216718";
		boolean res = p.ping(host);
		assertTrue(res);
		
	}
	
	@Test
	public void testPing2() {
		Ping p = new Ping();
		String hostslist="imsg-wks-211570 FAII-WKS-216713 FAII-WKS-216711 IMS-WKS-215229" 
			+" FAII-WKS-216714 FAII-WKS-216706 FAII-WKS-216709 IMSG-WKS-211572"
			+" FAII-WKS-216718 10.104.80.70 ims-wks-215229 FAII-WKS-216741 FAII-WKS-215032" 
			+" FAII-WKS-211573 FAIT-WKS-219983 FAIT-WKS-219982";
		String[] hosts = hostslist.split(" ");
		for (String host : hosts) {
			boolean res = p.ping(host);
			if(!res) {
				System.out.println(host + " unreachable");
			}
		}
		
		
	}
	
	@Test
	public void testPing_IA() {
		Ping p = new Ping();
		String hostslist="ipit-wks-206708 ipit-wks-206713 IPXD-WKS-214198 ipit-wks-206710 IPIG-WKS-205970 IPIH-WKS-205910 ipim-wks-206762" +
				" IP-WKS-214188 IPIC-WKS-214175 IA-WKS-350606 ipxi-wks-214196 PAC-WKS-350603 PAC-WKS-350602" +
				" ipit-wks-215444 IPIT-WKS-215443 ipig-wks-215464 IPIG-WKS-215457" +
				" IAIG-WKS-214186 ipig-wks-206037 IPIG-WKS-215432 IPIG-WKS-214185" +
				" IAIT-WKS-205944 IAIG-WKS-205971 IAP-WKS-211740 IAIG-WKS-215455" +
				" iaig-wks-206758 IPIC-WKS-215428 IPIG-WKS-217157 IAIT-WKS-220297" +
				" IAIT-WKS-218399 IAIT-WKS-215440 IPIT-WKS-214199 IAIT-WKS-219977";
		String[] hosts = hostslist.split(" ");
		int cu =0;
		for (String host : hosts) {
			boolean res = p.ping(host);
			if(!res) {
				cu++;
				System.out.println(cu + ". " + host + " unreachable");
			}			
		}
		System.out.println(String.format("Unreachables %s/%s",cu, hosts.length));
		
		
	}

}
