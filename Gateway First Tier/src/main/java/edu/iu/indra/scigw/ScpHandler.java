package edu.iu.indra.scigw;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.Session;

public class ScpHandler
{
	public void copyJobFilesToHost(String destFile, String sourceFile) throws Exception
	{
		boolean ptimestamp = true;

		FileInputStream fis = null;

		Connector connector = Connector.getInstance();

		Session session = connector.getSession();

		Channel channel = session.openChannel("exec");
		// exec 'scp -t rfile' remotely
		String command = "scp " + (ptimestamp ? "-p" : "") + " -t " + destFile;
		((ChannelExec) channel).setCommand(command);

		// get I/O streams for remote scp
		OutputStream out = channel.getOutputStream();
		InputStream in = channel.getInputStream();

		channel.connect();

		if (checkAck(in) != 0)
		{
			System.exit(0);
		}

		File _lfile = new File(sourceFile);

		if (ptimestamp)
		{
			command = "T " + (_lfile.lastModified() / 1000) + " 0";
			// The access time should be sent here,
			// but it is not accessible with JavaAPI ;-<
			command += (" " + (_lfile.lastModified() / 1000) + " 0\n");
			out.write(command.getBytes());
			out.flush();
			if (checkAck(in) != 0)
			{
				System.exit(0);
			}
		}

		// send "C0644 filesize filename", where filename should not include '/'
		long filesize = _lfile.length();
		command = "C0644 " + filesize + " ";
		if (sourceFile.lastIndexOf('/') > 0)
		{
			command += sourceFile.substring(sourceFile.lastIndexOf('/') + 1);
		} else
		{
			command += sourceFile;
		}
		command += "\n";
		out.write(command.getBytes());
		out.flush();
		if (checkAck(in) != 0)
		{
			System.exit(0);
		}

		// send a content of lfile
		fis = new FileInputStream(sourceFile);
		byte[] buf = new byte[1024];
		while (true)
		{
			int len = fis.read(buf, 0, buf.length);
			if (len <= 0)
				break;
			out.write(buf, 0, len); // out.flush();
		}
		fis.close();
		fis = null;
		// send '\0'
		buf[0] = 0;
		out.write(buf, 0, 1);
		out.flush();
		if (checkAck(in) != 0)
		{
			System.exit(0);
		}
		out.close();
		channel.disconnect();
	}

	static int checkAck(InputStream in) throws IOException
	{
		int b = in.read();
		// b may be 0 for success,
		// 1 for error,
		// 2 for fatal error,
		// -1
		if (b == 0)
			return b;
		if (b == -1)
			return b;

		if (b == 1 || b == 2)
		{
			StringBuffer sb = new StringBuffer();
			int c;
			do
			{
				c = in.read();
				sb.append((char) c);
			} while (c != '\n');
			if (b == 1)
			{ // error
				System.out.print(sb.toString());
			}
			if (b == 2)
			{ // fatal error
				System.out.print(sb.toString());
			}
		}
		return b;
	}

}
