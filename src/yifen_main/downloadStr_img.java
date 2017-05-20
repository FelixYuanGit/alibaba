package yifen_main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import yifen_main.ImageUtil;

public class downloadStr_img {

	public downloadStr_img() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * ��ȡͼƬURL
	 * 
	 * @param Url
	 *            ��Ʒ����ҳ����
	 * @param flieName
	 *            ָ��ͼƬҪ�ŵĵ�ַ
	 * 
	 */
	public static void dlimg(String Url, String flieName) {
		// "https://detail.1688.com/pic/40104948394.html?spm="
		Url = Url.replaceAll("offer", "pic");
		DefaultHttpClient client = new DefaultHttpClient();
		HttpGet gg = new HttpGet(Url);
		gg.setHeader("Connection", "keep-alive");
		gg.setHeader("Upgrade-Insecure-Requests", "1");
		gg.setHeader("User-Agent",
				"Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.75 Safari/537.36");
		gg.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		gg.setHeader("Accept-Language", "zh-CN,zh;q=0.8");
		HttpResponse set1 = null;
		String New = "";
		try {
			set1 = client.execute(gg);
			// System.out.println(set1);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			// System.out.println("1");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			New = changInputStream.changInput(set1.getEntity().getContent(), "GB2312");
		} catch (UnsupportedOperationException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// System.out.println(New);
		Document doc = Jsoup.parse(New);
		int i = 0;
		Elements url11 = doc.select("li.tab-trigger");
		for (Element link : url11) {
			i++;
			downloadStr_img.dl(link.attr("data-img"), flieName, i + "");
			// System.out.println(link.attr("data-img"));

		}
	}

	/**
	 * ����ͼƬ������
	 * 
	 * @param Url
	 *            ͼƬ��ַ
	 * @param flieName
	 *            �ļ�·��
	 * @param i
	 *            ��i��ͼƬ
	 */
	public static void dl(String Url, String flieName, String i) {
		DefaultHttpClient client1 = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(Url);
		System.out.println(Url);
		String Name = "";
		FileOutputStream fos;
		try {
			// �ͻ��˿�ʼ��ָ������ַ��������
			HttpResponse response = client1.execute(httpGet);
			InputStream inputStream = response.getEntity().getContent();
			File file = new File(flieName);
			if (!file.exists()) {
				file.mkdirs();
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Date nowTime = new Date(System.currentTimeMillis());
			// ��ȡϵͳʱ��
			SimpleDateFormat sdFormatter = new SimpleDateFormat("yyyyMMddHHmmss");
			Name = sdFormatter.format(nowTime);
			fos = new FileOutputStream(flieName + Name + "_" + i + ".jpg");
			byte[] data = new byte[1024];
			int len = 0;
			while ((len = inputStream.read(data)) != -1) {
				fos.write(data, 0, len);
			}
			fos.close();
			System.out.println("��ȡ����ͼƬ�ɹ���");
		} catch (IOException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
		}
		String path = flieName + Name + "_" + i + ".jpg";
		FileInputStream in = null;
		try {
			in = ImageUtil.readImage(path);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	/**
	 * ���ַ���д��ָ��·�����ļ�
	 * 
	 * @param flieName
	 *            ָ��·��
	 * @param str
	 *            ָ�����ַ���
	 */
	public static void xrstr(String flieName, String str) {
		File txt = new File(flieName);
		if (!txt.exists()) {
			try {
				txt.createNewFile();
				byte bytes[] = new byte[1024];
				bytes = str.getBytes(); // �¼ӵ�
				int b = str.length(); // ��
				FileOutputStream foss = new FileOutputStream(txt);
				foss.write(str.getBytes("UTF-8"));
				foss.close();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
