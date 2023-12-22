package com.is.tieto_globuz.file;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.is.ISLogger;
import com.is.LtLogger;
import com.is.tieto_globuz.agreements.AgreementService;
import com.is.tieto_globuz.agreements.On_us;
import com.is.tieto_globuz.agreements.WithMerchant;
import com.is.tieto_globuz.merchants.Merchant;
import com.is.tieto_globuz.merchants.MerchantService;
import com.is.tieto_globuz.terminals.Terminal;
import com.is.tieto_globuz.terminals.TerminalService;
import com.is.tieto_globuz.tieto.TclientService;
import com.is.utils.CheckNull;
import com.is.utils.Res;

public class FileWrite
{
	private static SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
	
	public static Res fileWrite(String file_path) throws IOException
	{
		FileWriter fileWriter = null;
		Res res = new Res();
		String data = "";
		String fileName = "";
		String fileNamePrev = "";
		String s_bank = "";
		try
		{
			Date date = new Date();
			String path_today;
			path_today = file_path + df.format(date).toString();
			
			File filePath = new File(path_today);
			
			if (!filePath.exists()) filePath.mkdir();
			
			s_bank = TclientService.getSBank();
			if (s_bank.length() == 0)
			{
				res.setCode(0);
				res.setName("ERROR while getting parametr s_bank from MerchantService");
				return res;
			}
			
			fileNamePrev = MerchantService.getLastFileName();
			if (fileNamePrev == null ) fileNamePrev = "";
			
			fileName = createFileName(s_bank, fileNamePrev);
			
			ISLogger.getLogger().error("fileName");
			
			List<Merchant> merchant = new ArrayList<Merchant>();
			merchant = MerchantService.getMerchant4Send();
			
			List<Terminal> terminals = new ArrayList<Terminal>();
			terminals = TerminalService.getTerminal4Send();
			
			List<WithMerchant> agreements = new ArrayList<WithMerchant>();
			agreements = AgreementService.getAgreements4Send();
			
			List<On_us> agreement2 = new ArrayList<On_us>();
			agreement2 = AgreementService.getOn_us4Send();
			
			if (merchant.size() != 0 || terminals.size() != 0 || agreements.size() != 0 || agreement2.size() != 0) 
			{
				data = setData(fileName, fileNamePrev, merchant, terminals, agreements, agreement2);
			}
			
			if (data.getBytes().length != 0)
			{
				File file = new File(path_today + "\\" + fileName);
				file.getParentFile().mkdirs();
				try
				{
					fileWriter = new FileWriter(file);
					fileWriter.write(data);
					
				}
				catch (Exception e)
				{
					res.setCode(0);
					res.setName("Файл не создан, отсутсвует папка GLOBUS " + e.getMessage());
					e.printStackTrace();
				}
				finally
				{
					try
					{
						fileWriter.close();
						System.out.println(file.getAbsolutePath() + " SIZE => " + file.length());
						if (file.length() > 0 )
						{
							Res ress = MerchantService.updateSts(merchant, fileName, "S");
							if (ress.getCode() == 0)
							{
								LtLogger.getLogger().error("ERROR MerchantService.updateSts");
								LtLogger.getLogger().error("ERROR Desc => " + res.getName());
								LtLogger.getLogger().error("ERROR fileName => " + fileName);
							}
							
							Res ress1 = TerminalService.updateSts(terminals, fileName, "S");
							if (ress1.getCode() == 0)
							{
								LtLogger.getLogger().error("ERROR TerminalService.updateSts");
								LtLogger.getLogger().error("ERROR Desc => " + res.getName());
								LtLogger.getLogger().error("ERROR fileName => " + fileName);
							}
							
							Res ress3 = AgreementService.updateStsWithM(agreements, fileName, "S");
							if (ress3.getCode() == 0)
							{
								LtLogger.getLogger().error("ERROR AgreementService.updateSts");
								LtLogger.getLogger().error("ERROR Desc => " + res.getName());
								LtLogger.getLogger().error("ERROR fileName => " + fileName);
							}
							
							Res ress2 = AgreementService.updateStsOnUs(agreement2, fileName, "S");
							if (ress2.getCode() == 0)
							{
								LtLogger.getLogger().error("ERROR AgreementService.updateSts");
								LtLogger.getLogger().error("ERROR Desc => " + res.getName());
								LtLogger.getLogger().error("ERROR fileName => " + fileName);
							}
							
							if (ress.getCode() == 1 && ress1.getCode() == 1) 
							{
								res.setCode(1);
								res.setName("Created File - " + fileName);
								LtLogger.getLogger().info("[" + new Date().toString() + "] Created File fileName => " + fileName);
							}
						}
					}
					catch (IOException e)
					{
						e.printStackTrace();
						LtLogger.getLogger().error("ERROR IOException => " + e.getMessage());
						res.setCode(0);
						res.setName(e.getMessage());
					}
				}
			}
			else
			{
				System.out.println("ERROR File Size => " + data.getBytes().length);
				res.setCode(0);
				res.setName("There is no new data fo send !");
			}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			res.setCode(0);
			res.setName(e.getMessage());
			
		}
		return res;
	}
	
	private static String createFileName(String bankCode, String fileNamePrev)
	{
		String newFileName = "";
		String temp_N = "";
		String temp_T = "";
		String temp_X = "";
		Date date = new Date();
		SimpleDateFormat ddd = new SimpleDateFormat("DDD");
		
		newFileName += "U";
//		newFileName += bankCode;
//		newFileName += "003";
//		newFileName += "028";
//		newFileName += "029";
//		newFileName += "024";
		newFileName += "026";
		newFileName += df.format(date).substring(3, 4) + ddd.format(date) + ".";
		
		if (!fileNamePrev.equals(""))
		{
			temp_N = fileNamePrev.substring(11, 12);
			temp_T = fileNamePrev.substring(9, 10);
			temp_X = fileNamePrev.substring(10, 11);
			
			// .TXN
			if (temp_T.equals("T") && temp_X.equals("X"))
			{
				if (!temp_N.equals("9"))
				{
					newFileName += "TX";
					newFileName += String.valueOf(Integer.parseInt(temp_N) + 1) ;
				}
				else
				{
					newFileName += "T";
					newFileName += String.valueOf(Integer.parseInt(temp_N) + 1) ;
				}
			}
			else if(temp_T.equals("T") && !temp_X.equals("X"))
			{
				if (!temp_X.equals("99"))
				{
					newFileName += "T";
					newFileName += String.valueOf(Integer.parseInt(fileNamePrev.substring(10, 12)) + 1) ;
				}
				else
				{
					newFileName += String.valueOf(Integer.parseInt(fileNamePrev.substring(10, 12)) + 1) ;
				}
			}
			else
			{
				newFileName += String.valueOf(Integer.parseInt(fileNamePrev.substring(9, 12)) + 1) ;
			}
		}
		else
		{
			newFileName += "TX";
			newFileName += "0";
		}
		
		return newFileName;
		
	}
	
	private static String setData(String fileName, String fileNamePrev
		, List<Merchant> merchant , List<Terminal> terminals, List<WithMerchant> agreements, List<On_us> agreement2)
	{
		String data = "";
		
		try
		{	//3.1 Заголовок U-файла
			data += "00";
			data += fileName;
			data += !fileNamePrev.equals("") ? fileNamePrev : addEmpty("0", 12);
			data += "0100" + System.getProperty("line.separator");
			
			//3.2 Структура записи U-файла
			for (int i = 0; i < merchant.size(); i++)
			{
				data += addEmpty("MERCHANTS_ALL", 15);
				data += addEmpty(merchant.get(i).getAction(), 1);
				data += addEmpty("0" + merchant.get(i).getMerchant().substring(1), 15);
				data += addEmpty("0" + merchant.get(i).getParent().substring(1), 15);
				data += addEmpty(merchant.get(i).getAbrv_name(), 27);
				data += addEmpty(merchant.get(i).getFax(), 11);
				data += addEmpty(merchant.get(i).getFull_name(), 78);
				//data += addEmpty(MerchantService.getNameCountry(merchant.get(i).getCntry()), 3);
				data += addEmpty(merchant.get(i).getCntry(), 3);
				data += addEmpty(merchant.get(i).getCity(), 15);
				data += addEmpty(merchant.get(i).getReg_nr(), 25);
				data += addEmpty(merchant.get(i).getStreet(), 30);
				data += addEmpty(merchant.get(i).getPost_ind(), 6);
				data += addEmpty(merchant.get(i).getPhone(), 11);
				data += addEmpty(merchant.get(i).getCont_person(), 30);
				data += addEmpty(merchant.get(i).getMcc(), 4);
				//data += addEmpty(MerchantService.getNameCountry(merchant.get(i).getP_cntry()), 3);
				data += addEmpty(merchant.get(i).getP_cntry(), 3);
				data += addEmpty(merchant.get(i).getP_city(), 15);
				data += addEmpty(merchant.get(i).getP_street(), 30);
				data += addEmpty(merchant.get(i).getP_post_ind(), 6);
				data += addEmpty(merchant.get(i).getMrc_phone(), 11);
				data += addEmpty(merchant.get(i).getReport_crit(), 1);
				data += addEmpty(merchant.get(i).getE_mail(), 256);
				data += addEmpty(merchant.get(i).getAdd_info(), 30);
				data += addEmpty(merchant.get(i).getReport_crit2(), 4);
				data += addEmpty(merchant.get(i).getUser_field(), 10) + System.getProperty("line.separator");
			}
			
			for(int i = 0; i < agreements.size(); i++)
			{
				data += addEmpty("AGREEMENTS_ALL", 15);
				data += addEmpty(agreements.get(i).getAction(), 1);
				data += addEmpty(agreements.get(i).getCard_type(), 2);
				data += addEmpty("0" + agreements.get(i).getMerchant().substring(1), 15);
				data += addEmpty(agreements.get(i).getAcq_bank(), 2);
				data += addEmpty(agreements.get(i).getAcq_branch(), 3);
				data += addEmpty(agreements.get(i).getTr_ccy(), 3);
				data += addEmpty(agreements.get(i).getImprint_fee(), 6);
				data += addEmpty(agreements.get(i).getImprint_min(), 8);
				data += addEmpty(agreements.get(i).getImprint_max(), 12);
				data += addEmpty(agreements.get(i).getPos_fee(), 6);
				data += addEmpty(agreements.get(i).getPos_min(), 8);
				data += addEmpty(agreements.get(i).getPos_max(), 12);
				data += addEmpty(agreements.get(i).getCashback_fee(), 6);
				data += addEmpty(agreements.get(i).getCasback_min(), 8);
				data += addEmpty(agreements.get(i).getCashback_max(), 12);
				data += addEmpty(agreements.get(i).getAtm_fee(), 6);
				data += addEmpty(agreements.get(i).getAtm_min(), 8);
				data += addEmpty(agreements.get(i).getAtm_max(), 12);
				data += addEmpty(agreements.get(i).getPos_limit(), 12);
				data += addEmpty(agreements.get(i).getPos_limit_act(), 12);
				data += addEmpty(agreements.get(i).getImprint_limit(), 12);
				data += addEmpty(agreements.get(i).getStatus(), 1);
				data += addEmpty(agreements.get(i).getAgr_number(), 15);
				data += addEmpty(df.format(agreements.get(i).getAgr_date()).toString(), 10);
				data += addEmpty(agreements.get(i).getAlgorithm(), 1);
				data += addEmpty(agreements.get(i).getOrder_period(), 2);
				data += addEmpty(agreements.get(i).getBank_pos_fee(), 6);
				data += addEmpty(agreements.get(i).getBank_pos_min(), 8);
				data += addEmpty(agreements.get(i).getBank_pos_max(), 12);
				data += addEmpty(agreements.get(i).getBank_imp_fee(), 6);
				data += addEmpty(agreements.get(i).getBank_imp_min(), 8);
				data += addEmpty(agreements.get(i).getBank_imp_max(), 12);
				data += addEmpty(agreements.get(i).getBank_atm_fee(), 6);
				data += addEmpty(agreements.get(i).getBank_atm_min(), 8);
				data += addEmpty(agreements.get(i).getBank_atm_max(), 12);
				data += addEmpty(agreements.get(i).getBank_algorithm(), 1);
				data += addEmpty(agreements.get(i).getBank_calc_mode(), 1);
				data += addEmpty(agreements.get(i).getBank_account(), 25) + System.getProperty("line.separator");;
			}
			
			for(int i = 0; i < agreement2.size(); i++)
			{
				data += addEmpty("AGR_ON_US_ALL", 15);
				data += addEmpty(agreement2.get(i).getAction(), 1);
				data += addEmpty(agreement2.get(i).getCard_type(), 2);
				data += addEmpty("0" + agreement2.get(i).getMerchant().substring(1), 15);
				data += addEmpty(agreement2.get(i).getIss_cmi(), 8);
				data += addEmpty(agreement2.get(i).getImprint_fee(), 6);
				data += addEmpty(agreement2.get(i).getImprint_min(), 8);
				data += addEmpty(agreement2.get(i).getImprint_max(), 12);
				data += addEmpty(agreement2.get(i).getPos_fee(), 6);
				data += addEmpty(agreement2.get(i).getPos_min(), 8);
				data += addEmpty(agreement2.get(i).getPos_max(), 12);
				data += addEmpty(agreement2.get(i).getCashback_fee(), 6);
				data += addEmpty(agreement2.get(i).getCashback_min(), 8);
				data += addEmpty(agreement2.get(i).getCashback_max(), 12);
				data += addEmpty(agreement2.get(i).getAtm_fee(), 6);
				data += addEmpty(agreement2.get(i).getAtm_min(), 8);
				data += addEmpty(agreement2.get(i).getAtm_max(), 12);
				data += addEmpty(agreement2.get(i).getTr_ccy(), 3);
				data += addEmpty(agreement2.get(i).getBin(), 15);
				data += addEmpty(agreement2.get(i).getAlgorithm(), 1);
				data += System.getProperty("line.separator");
			}
			
			for (int i = 0; i < terminals.size(); i++)
			{
				data += addEmpty("SET0_ACC_TR_ALL", 15);
				data += addEmpty(terminals.get(i).getAction(), 1);
				data += addEmpty(terminals.get(i).getTerminal_id(), 8);
				data += addEmpty("0" + terminals.get(i).getAcceptor_id().substring(1), 15);
				data += addEmpty(terminals.get(i).getTerm_type(), 12);
				data += addEmpty(terminals.get(i).getPoint_code(), 12);
				data += addEmpty(terminals.get(i).getInstall_date(), 8);
				data += addEmpty(terminals.get(i).getStatus(), 1);
				data += addEmpty(terminals.get(i).getSerial_nr(), 24);
				data += addEmpty(terminals.get(i).getInv_nr(), 24);
				data += addEmpty(terminals.get(i).getNotes(), 150);
				data += System.getProperty("line.separator");
			}
			
			//3.4 Трейлер U-файла
			data += "99";
			data += fileName;			
			String input = "" + (merchant.size() + terminals.size() + agreement2.size() + agreements.size());
            data += "00000".substring(input.length()) + input + System.getProperty("line.separator");
			
		}
		catch (Exception e)
		{
			data = "";
			e.printStackTrace();
		}
		
		return data;
	}
	
	private static String addEmpty(String name, int lentgh)
	{
		int diff = 0;
		try
		{
			if (CheckNull.isEmpty(name)) name = "";
			diff = lentgh - name.length();
			for (int i = 0; i < diff; i++)
			{
				name += " ";
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("ERROR on name => " + name);
			System.out.println("ERROR on lentgh => " + lentgh);
		}
		return name;
	}
	
}
