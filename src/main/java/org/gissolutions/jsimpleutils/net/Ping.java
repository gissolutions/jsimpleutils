package org.gissolutions.jsimpleutils.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

public class Ping {
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(Ping.class);
	private List<String> hosts;
	private boolean verbose = false;

	public Ping(boolean verbose) {
		super();
		this.verbose = verbose;
	}

	public Ping() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public boolean ping(String host) {
		int timeOut =3000;
		try {
			return InetAddress.getByName(host).isReachable(timeOut);
		} catch (UnknownHostException e) {
			String msg = "%s: %s";
			msg = String.format(msg, e.getClass().getName(), e.getMessage());
			logger.error(msg);
		} catch (IOException e) {
			String msg = "%s: %s";
			msg = String.format(msg, e.getClass().getName(), e.getMessage());
			logger.error(msg);
		}
		return false;
	}
	@Deprecated
	public boolean ping2(String host) {
		String pingCmd = "ping " + host;
		String pingResult = "";

		try {
			Runtime r = Runtime.getRuntime();
			Process p = r.exec(pingCmd);

			BufferedReader in = new BufferedReader(new InputStreamReader(p
					.getInputStream()));
			String inputLine;
			int c = 1;
			String okResult = "    Packets: Sent = 4, Received = 4, Lost = 0 (0% loss),";
			String result = "";
			while ((inputLine = in.readLine()) != null) {
				if (inputLine.length() != 0) {
					c++ ;
					if (verbose) {
						System.out.println(String.format("%s. [l:%s] %s",
								c , inputLine.length(), inputLine));
						pingResult += inputLine;
					}
					if (c == 8) {
						result = inputLine;
					}
				}
			}
			in.close();
			return result.equals(okResult);
		}// try
		catch (IOException e) {
			System.out.println(e);
			return false;
		}

	}
}
