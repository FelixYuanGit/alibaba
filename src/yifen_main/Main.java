package yifen_main;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.apache.http.impl.client.DefaultHttpClient;

public class Main {

	public static void main(String[] a) {
		// https://hanyan.1688.com/page/offerlist.htm?spm=&pageNum=3#search-bar

		String hu = "";
		String main_url = "";
		Scanner in = new Scanner(System.in); // Scanner��
		String YN = "";
		while (!YN.equals("Y")) {
			System.out.print("�����밢���̼ҵ�ַ��");
			main_url = in.next();
			System.out.print("��ȷ�ϸ���ַ�Ƿ���ȷ" + "(Y/N)");
			YN = in.next();
		}
		System.out.print("������Ҫ����ҳ����");
		int page = in.nextInt();
		System.out.print("��ʼ��ȡ���ݣ�");
		//main_url = "https://shop1382633835710.1688.com";// https://caiyunxuanfushi.1688.com
		String shop_Url = main_url + "/page/offerlist.htm";
		int good_num = 0;
		for (int k = 1; k < page; k++) {
			if (k > 1) {
				shop_Url = main_url + "/page/offerlist.htm";
				shop_Url = shop_Url + "?spm=&pageNum=" + k + "#search-bar";
			}
			Map<String, String> good_Map = new HashMap<String, String>();
			DefaultHttpClient client = new DefaultHttpClient();
			// ��ȡ����Ʒ�б�ҳ��
			// String shop_Url = get_ShopClassify.get_shopUrl(client, main_url);

			System.out.println("shop_Url-->" + shop_Url);
			// ���������Ʒ�б�����Ʒ����
			good_Map = json_good.good_List(client, shop_Url);

			for (Map.Entry<String, String> entry : good_Map.entrySet()) {
				good_num++;
				String url = entry.setValue(good_num + "");
				System.out.println("���ڻ�ȡ��" + good_num + "��Ʒ��Ϣ��\n	" + url);
				if (json_good.good_info(client, url,good_num)) {
					System.out.println("\n��ȡ�ɹ���");
				} else {
					System.out.println("\n��ȡʧ��");
				}

			}

		}
	}

}
