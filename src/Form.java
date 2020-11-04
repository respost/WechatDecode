import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.SystemColor;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

public class Form {

	private JFrame frame;
	private JTextField txt_wx_path;
	private JTextField txt_save_path;
	private JButton btn_decode;
	private JTextArea txtArea_log;
	JFileChooser jfc = new JFileChooser();// 文件选择器  


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Form window = new Form();
					window.frame.setVisible(true);
					//JFrame设置标题
					window.frame.setTitle("微信dat文件解析工具 ( 开发者QQ：15577969 )"); 
					//JFrame关闭窗口最大化
					window.frame.setResizable(false);
					//JFrame打开后居中
					window.frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Form() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		String imagePath = "image/wx.png";
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource(imagePath)));
		frame.setBounds(100, 100, 555, 415);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lab_wx_path = new JLabel("\u5FAE\u4FE1\u6587\u4EF6\u8DEF\u5F84\uFF1A");
		lab_wx_path.setHorizontalAlignment(SwingConstants.CENTER);
		lab_wx_path.setForeground(Color.GRAY);
		lab_wx_path.setBounds(15, 23, 95, 15);
		frame.getContentPane().add(lab_wx_path);
		
		JLabel lab_save_path = new JLabel("\u89E3\u6790\u4FDD\u5B58\u8DEF\u5F84\uFF1A");
		lab_save_path.setHorizontalAlignment(SwingConstants.CENTER);
		lab_save_path.setForeground(Color.GRAY);
		lab_save_path.setBounds(15, 58, 95, 15);
		frame.getContentPane().add(lab_save_path);
		
		txt_wx_path = new JTextField();
		txt_wx_path.setText("C:\\Users\\Administrator\\Documents\\WeChat Files");
		txt_wx_path.setBounds(108, 20, 392, 21);
		frame.getContentPane().add(txt_wx_path);
		txt_wx_path.setColumns(10);
		
		txt_save_path = new JTextField();
		txt_save_path.setText("C:\\Users\\Administrator\\Desktop");
		txt_save_path.setBounds(108, 55, 392, 21);
		frame.getContentPane().add(txt_save_path);
		txt_save_path.setColumns(10);
		
		btn_decode = new JButton("\u70B9\u51FB\u5F00\u59CB\u89E3\u6790\u5FAE\u4FE1dat\u6587\u4EF6");
		//点击解析按钮
		btn_decode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//微信文件路径
				String wxPath=txt_wx_path.getText();
				//保存路径（输出）
				String savePath=txt_save_path.getText();
				//开始解析
				convert(wxPath, savePath);
			}
		});
		btn_decode.setBounds(15, 90, 520, 31);
		frame.getContentPane().add(btn_decode);
	
		txtArea_log = new JTextArea();
		txtArea_log.setEditable(false);
		txtArea_log.setForeground(new Color(0, 255, 0));
		txtArea_log.setBackground(SystemColor.textInactiveText);
		txtArea_log.setBounds(15, 131, 520, 236);
		frame.getContentPane().add(txtArea_log);
		
		btn_open_wxpath = new JButton("");
		btn_open_wxpath.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource().equals(btn_open_wxpath)) {// 判断触发方法的按钮是哪个  
		            jfc.setFileSelectionMode(1);// 设定只能选择到文件夹  
		            int state = jfc.showOpenDialog(null);// 此句是打开文件选择器界面的触发语句  
		            if (state == 1) {  
		                return;  
		            } else {  
		                File f = jfc.getSelectedFile();// f为选择到的目录  
		                txt_wx_path.setText(f.getAbsolutePath());  
		            }  
		        }  
			}
		});
		btn_open_wxpath.setToolTipText("\u9009\u62E9\u5FAE\u4FE1\u76EE\u5F55");
		String icoPath = "image/30_20.png";
		btn_open_wxpath.setIcon(new ImageIcon(getClass().getClassLoader().getResource(icoPath)));
		btn_open_wxpath.setBounds(505, 18, 30, 20);
		/*****************************************************/ 
		//去掉焦点边框
		btn_open_wxpath.setFocusPainted(false);
		//去掉按钮填充（按钮透明）
		btn_open_wxpath.setContentAreaFilled(false);
		//去掉边框
		btn_open_wxpath.setBorderPainted(false);
		/*****************************************************/  		
		frame.getContentPane().add(btn_open_wxpath);
		btn_open_savepath.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource().equals(btn_open_savepath)) {// 判断触发方法的按钮是哪个  
		            jfc.setFileSelectionMode(1);// 设定只能选择到文件夹  
		            int state = jfc.showOpenDialog(null);// 此句是打开文件选择器界面的触发语句  
		            if (state == 1) {  
		                return;  
		            } else {  
		                File f = jfc.getSelectedFile();// f为选择到的目录  
		                txt_save_path.setText(f.getAbsolutePath());  
		            }  
		        }  
			}
		});
		btn_open_savepath.setToolTipText("\u9009\u62E9\u4FDD\u5B58\u76EE\u5F55");
		btn_open_savepath.setIcon(new ImageIcon(getClass().getClassLoader().getResource(icoPath)));
		btn_open_savepath.setBounds(505, 55, 30, 20);
		/*****************************************************/ 
		//去掉焦点边框
		btn_open_savepath.setFocusPainted(false);
		//去掉按钮填充（按钮透明）
		btn_open_savepath.setContentAreaFilled(false);
		//去掉边框
		btn_open_savepath.setBorderPainted(false);
		/*****************************************************/ 
		frame.getContentPane().add(btn_open_savepath);
	}
	/**
     * @param path       图片目录地址
     * @param targetPath 转换后目录
     */
    private void convert(String path, String targetPath) {
        File[] file = new File(path).listFiles();
        if (file == null) {
            return;
        }
        int size = file.length;
        txtArea_log.append("总共" + size + "个文件\r\n");
        AtomicReference<Integer> integer = new AtomicReference<>(0);
        AtomicInteger x = new AtomicInteger();
        for (File file1 : file) {
            if (file1.isFile()) {
                Object[] xori = getXor(file1);
                if (xori != null && xori[1] != null){
                    x.set((int)xori[1]);
                }
                break;
            }
        }
        Arrays.stream(file).parallel().forEach(file1 -> {
            if (file1.isDirectory()) {
                String[] newTargetPath = file1.getPath().split("/|\\\\");
                File targetFile = new File(targetPath+File.separator+newTargetPath[newTargetPath.length - 1]);
                if (!targetFile.exists()) {
                    targetFile.mkdirs();
                }
                convert(file1.getPath(),targetPath+File.separator+newTargetPath[newTargetPath.length - 1]);
                return;
            }
            Object[] xor = getXor(file1);
            if (x.get() == 0 && xor[1] != null && (int) xor[1] != 0) {
                x.set((int) xor[1]);
            }
            xor[1] = xor[1] == null ? x.get() : xor[1];
            try (InputStream reader = new FileInputStream(file1);
                 OutputStream writer =
                         new FileOutputStream(targetPath + File.separator + file1.getName().split("\\.")[0] + (xor[0] != null ?
                                 "." + xor[0] : ""))) {
                byte[] bytes = new byte[1024 * 10];
                int b;
                while ((b = reader.read(bytes)) != -1) {//这里的in.read(bytes);就是把输入流中的东西，写入到内存中（bytes）。
                    for (int i = 0; i < bytes.length; i++) {
                        bytes[i] = (byte) (int) (bytes[i] ^ (int) xor[1]);
                        if (i == (b - 1)) {
                            break;
                        }
                    }
                    writer.write(bytes, 0, b);
                    writer.flush();
                }
                integer.set(integer.get() + 1);
                txtArea_log.append(file1.getName() + "(大小:" + ((double) file1.length() / 1000) + "kb,异或值:" + xor[1] + ")," +"进度：" + integer.get() + "/" + size+"\r\n");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        txtArea_log.append("解析完毕！\r\n");
    }

    /**
     * 判断图片异或值
     *
     * @param file
     * @return
     */
    private static Object[] getXor(File file) {
        Object[] xor = null;
        if (file != null) {
            byte[] bytes = new byte[4];
            try (InputStream reader = new FileInputStream(file)) {
                reader.read(bytes, 0, bytes.length);
            } catch (Exception e) {
                e.printStackTrace();
            }
            xor = getXor(bytes);
        }
        return xor;
    }

    /**
     * @param bytes
     * @return
     */
    private static Object[] getXor(byte[] bytes) {
        Object[] xorType = new Object[2];
        int[] xors = new int[3];
        for (Map.Entry<String, String> type : FILE_TYPE_MAP.entrySet()) {
            String[] hex = {
                    String.valueOf(type.getKey().charAt(0)) + type.getKey().charAt(1),
                    String.valueOf(type.getKey().charAt(2)) + type.getKey().charAt(3),
                    String.valueOf(type.getKey().charAt(4)) + type.getKey().charAt(5)
            };
            xors[0] = bytes[0] & 0xFF ^ Integer.parseInt(hex[0], 16);
            xors[1] = bytes[1] & 0xFF ^ Integer.parseInt(hex[1], 16);
            xors[2] = bytes[2] & 0xFF ^ Integer.parseInt(hex[2], 16);
            if (xors[0] == xors[1] && xors[1] == xors[2]) {
                xorType[0] = type.getValue();
                xorType[1] = xors[0];
                break;
            }
        }
        return xorType;
    }

    private final static Map<String, String> FILE_TYPE_MAP = new HashMap<String, String>();
    private JButton btn_open_wxpath;
    private final JButton btn_open_savepath = new JButton("");

    static {
        getAllFileType();
    }

    private static void getAllFileType() {
        FILE_TYPE_MAP.put("ffd8ffe000104a464946", "jpg"); //JPEG (jpg)
        FILE_TYPE_MAP.put("89504e470d0a1a0a0000", "png"); //PNG (png)
        FILE_TYPE_MAP.put("47494638396126026f01", "gif"); //GIF (gif)
        FILE_TYPE_MAP.put("49492a00227105008037", "tif"); //TIFF (tif)
        FILE_TYPE_MAP.put("424d228c010000000000", "bmp"); //16色位图(bmp)
        FILE_TYPE_MAP.put("424d8240090000000000", "bmp"); //24位位图(bmp)
        FILE_TYPE_MAP.put("424d8e1b030000000000", "bmp"); //256色位图(bmp)
        FILE_TYPE_MAP.put("41433130313500000000", "dwg"); //CAD (dwg)
        FILE_TYPE_MAP.put("3c21444f435459504520", "html"); //HTML (html)
        FILE_TYPE_MAP.put("3c21646f637479706520", "htm"); //HTM (htm)
        FILE_TYPE_MAP.put("48544d4c207b0d0a0942", "css"); //css
        FILE_TYPE_MAP.put("696b2e71623d696b2e71", "js"); //js
        FILE_TYPE_MAP.put("7b5c727466315c616e73", "rtf"); //Rich Text Format (rtf)
        FILE_TYPE_MAP.put("38425053000100000000", "psd"); //Photoshop (psd)
        FILE_TYPE_MAP.put("46726f6d3a203d3f6762", "eml"); //Email [Outlook Express 6] (eml)
        FILE_TYPE_MAP.put("d0cf11e0a1b11ae10000", "doc"); //MS Excel 注意：word、msi 和 excel的文件头一样
        FILE_TYPE_MAP.put("d0cf11e0a1b11ae10000", "vsd"); //Visio 绘图
        FILE_TYPE_MAP.put("5374616E64617264204A", "mdb"); //MS Access (mdb)
        FILE_TYPE_MAP.put("252150532D41646F6265", "ps");
        FILE_TYPE_MAP.put("255044462d312e360d25", "pdf"); //Adobe Acrobat (pdf)
        FILE_TYPE_MAP.put("2e524d46000000120001", "rmvb"); //rmvb/rm相同
        FILE_TYPE_MAP.put("464c5601050000000900", "flv"); //flv与f4v相同
        FILE_TYPE_MAP.put("00000020667479706973", "mp4");
        FILE_TYPE_MAP.put("49443303000000000f76", "mp3");
        FILE_TYPE_MAP.put("000001ba210001000180", "mpg"); //
        FILE_TYPE_MAP.put("3026b2758e66cf11a6d9", "wmv"); //wmv与asf相同
        FILE_TYPE_MAP.put("524946464694c9015741", "wav"); //Wave (wav)
        FILE_TYPE_MAP.put("52494646d07d60074156", "avi");
        FILE_TYPE_MAP.put("4d546864000000060001", "mid"); //MIDI (mid)
        FILE_TYPE_MAP.put("504b0304140000000800", "zip");
        FILE_TYPE_MAP.put("526172211a0700cf9073", "rar");
        FILE_TYPE_MAP.put("235468697320636f6e66", "ini");
        FILE_TYPE_MAP.put("504b03040a0000000000", "jar");
        FILE_TYPE_MAP.put("4d5a9000030000000400", "exe");//可执行文件
        FILE_TYPE_MAP.put("3c25402070616765206c", "jsp");//jsp文件
        FILE_TYPE_MAP.put("4d616e69666573742d56", "mf");//MF文件
        FILE_TYPE_MAP.put("3c3f786d6c2076657273", "xml");//xml文件
        FILE_TYPE_MAP.put("efbbbf2f2a0d0a53514c", "sql");//xml文件
        FILE_TYPE_MAP.put("7061636b616765207765", "java");//java文件
        FILE_TYPE_MAP.put("406563686f206f66660d", "bat");//bat文件
        FILE_TYPE_MAP.put("1f8b0800000000000000", "gz");//gz文件
        FILE_TYPE_MAP.put("6c6f67346a2e726f6f74", "properties");//bat文件
        FILE_TYPE_MAP.put("cafebabe0000002e0041", "class");//bat文件
        FILE_TYPE_MAP.put("49545346030000006000", "chm");//bat文件
        FILE_TYPE_MAP.put("04000000010000001300", "mxp");//bat文件
        FILE_TYPE_MAP.put("504b0304140006000800", "docx");//docx文件
        FILE_TYPE_MAP.put("d0cf11e0a1b11ae10000", "wps");//WPS文字wps、表格et、演示dps都是一样的
        FILE_TYPE_MAP.put("6431303a637265617465", "torrent");
        FILE_TYPE_MAP.put("494d4b48010100000200", "264");

        FILE_TYPE_MAP.put("6D6F6F76", "mov"); //Quicktime (mov)
        FILE_TYPE_MAP.put("FF575043", "wpd"); //WordPerfect (wpd)
        FILE_TYPE_MAP.put("CFAD12FEC5FD746F", "dbx"); //Outlook Express (dbx)
        FILE_TYPE_MAP.put("2142444E", "pst"); //Outlook (pst)
        FILE_TYPE_MAP.put("AC9EBD8F", "qdf"); //Quicken (qdf)
        FILE_TYPE_MAP.put("E3828596", "pwl"); //Windows Password (pwl)
        FILE_TYPE_MAP.put("2E7261FD", "ram"); //Real Audio (ram)
    }
}
