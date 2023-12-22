package com.is.file_reciever_srv.recievers.EMPC.b_file.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.is.file_reciever_srv.recievers.EMPC.b_file.entity.B_file_transaction_record;

public class B_file_transaction_DAO
{
	public static B_file_transaction_record read(B_file_transaction_record rec, String line) throws SQLException
	{
		return new B_file_transaction_record(
				rec.getEMPC_file_id(), 
				0l,
				0l,
				line.length() >= 2 ? line.substring(0, 2).trim() : null, 
				line.length() >= 4 ? line.substring(2, 4).trim() : null, 
				line.length() >= 6 ? line.substring(4, 6).trim() : null, 
				line.length() >= 14 ? line.substring(6, 14).trim() : null, 
				line.length() >= 22 ? line.substring(14, 22).trim() : null,
				line.length() >= 30 ? line.substring(22, 30).trim() : null,
				line.length() >= 32 ? line.substring(30, 32).trim() : null,
				line.length() >= 35 ? line.substring(32, 35).trim() : null,
				line.length() >= 36 ? line.substring(35, 36).trim() : null,
				line.length() >= 38 ? line.substring(36, 38).trim() : null,
				line.length() >= 45 ? line.substring(38, 45).trim() : null,
				line.length() >= 52 ? line.substring(45, 52).trim() : null,
				line.length() >= 59 ? line.substring(52, 59).trim() : null,
				line.length() >= 78 ? line.substring(59, 78).trim() : null,
				line.length() >= 82 ? line.substring(78, 82).trim() : null,
				line.length() >= 90 ? line.substring(82, 90).trim() : null,
				line.length() >= 96 ? line.substring(90, 96).trim() : null,
				line.length() >= 98 ? line.substring(96, 98).trim() : null,
				line.length() >= 104 ? line.substring(98, 104).trim() : null,
				line.length() >= 105 ? line.substring(104, 105).trim() : null,
				line.length() >= 111 ? line.substring(105, 111).trim() : null,
				line.length() >= 123 ? line.substring(111, 123).trim() : null,
				line.length() >= 135 ? line.substring(123, 135).trim() : null,
				line.length() >= 147 ? line.substring(135, 147).trim() : null,
				line.length() >= 157 ? line.substring(147, 157).trim() : null,
				line.length() >= 160 ? line.substring(157, 160).trim() : null,
				line.length() >= 161 ? line.substring(160, 161).trim() : null,
				line.length() >= 173 ? line.substring(161, 173).trim() : null,
				line.length() >= 185 ? line.substring(173, 185).trim() : null,
				line.length() >= 195 ? line.substring(185, 195).trim() : null,
				line.length() >= 198 ? line.substring(195, 198).trim() : null,
				line.length() >= 199 ? line.substring(198, 199).trim() : null,
				line.length() >= 213 ? line.substring(199, 213).trim() : null,
				line.length() >= 221 ? line.substring(213, 221).trim() : null,
				line.length() >= 233 ? line.substring(221, 233).trim() : null,
				line.length() >= 245 ? line.substring(233, 245).trim() : null,
				line.length() >= 255 ? line.substring(245, 255).trim() : null,
				line.length() >= 258 ? line.substring(255, 258).trim() : null,
				line.length() >= 259 ? line.substring(258, 259).trim() : null,
				line.length() >= 273 ? line.substring(259, 273).trim() : null,
				line.length() >= 281 ? line.substring(273, 281).trim() : null,
				line.length() >= 308 ? line.substring(281, 308).trim() : null,
				line.length() >= 323 ? line.substring(308, 323).trim() : null,
				line.length() >= 326 ? line.substring(323, 326).trim() : null,
				line.length() >= 338 ? line.substring(326, 338).trim() : null,
				line.length() >= 342 ? line.substring(338, 342).trim() : null,
				line.length() >= 343 ? line.substring(342, 343).trim() : null,
				line.length() >= 354 ? line.substring(343, 354).trim() : null,
				line.length() >= 365 ? line.substring(354, 365).trim() : null,
				line.length() >= 373 ? line.substring(365, 373).trim() : null,
				line.length() >= 396 ? line.substring(373, 396).trim() : null,
				line.length() >= 414 ? line.substring(396, 414).trim() : null,
				line.length() >= 422 ? line.substring(414, 422).trim() : null,
				line.length() >= 430 ? line.substring(422, 430).trim() : null,
				line.length() >= 431 ? line.substring(430, 431).trim() : null,
				line.length() >= 441 ? line.substring(433, 441).trim() : null,
				line.length() >= 449 ? line.substring(441, 449).trim() : null, 
				line.length() >= 455 ? line.substring(449, 455).trim() : null,
				line.length() >= 467 ? line.substring(455, 467).trim() : null,
				line.length() >= 479 ? line.substring(467, 479).trim() : null,
				line.length() >= 489 ? line.substring(479, 489).trim() : null,
				line.length() >= 492 ? line.substring(489, 492).trim() : null,
				line.length() >= 493 ? line.substring(492, 493).trim() : null,
				line.length() >= 507 ? line.substring(493, 507).trim() : null,
				line.length() >= 515 ? line.substring(507, 515).trim() : null,
				line.length() >= 516 ? line.substring(515, 516).trim() : null,
				line.length() >= 517 ? line.substring(516, 517).trim() : null,
				line.length() >= 521 ? line.substring(517, 521).trim() : null,
				line.length() >= 524 ? line.substring(521, 524).trim() : null,
				line.length() >= 528 ? line.substring(524, 528).trim() : null,
				line.length() >= 532 ? line.substring(528, 532).trim() : null,
				line.length() >= 534 ? line.substring(532, 534).trim() : null,
				line.length() >= 535 ? line.substring(534, 535).trim() : null,
				line.length() >= 550 ? line.substring(535, 550).trim() : null,
				line.length() >= 551 ? line.substring(550, 551).trim() : null,
				line.length() >= 552 ? line.substring(551, 552).trim() : null,
				line.length() >= 563 ? line.substring(552, 563).trim() : null,
				line.length() >= 574 ? line.substring(563, 574).trim() : null,
				line.length() >= 673 ? line.substring(574, 673).trim() : null,
				line.length() >= 698 ? line.substring(673, 698).trim() : null,
				line.length() >= 726 ? line.substring(698, 726).trim() : null,
				line.length() >= 754 ? line.substring(726, 754).trim() : null,
				line.length() >= 854 ? line.substring(754, 854).trim() : null,
				line.length() >= 857 ? line.substring(854, 857).trim() : null,
				line.length() >= 861 ? line.substring(857, 861).trim() : null,
				line.length() >= 869 ? line.substring(861, 869).trim() : null,
				line.length() >= 879 ? line.substring(869, 879).trim() : null,
				line.length() >= 882 ? line.substring(879, 882).trim() : null,
				line.length() >= 883 ? line.substring(882, 883).trim() : null,
				line.length() >= 884 ? line.substring(883, 884).trim() : null,
				line.length() >= 890 ? line.substring(884, 890).trim() : null,
				line.length() >= 900 ? line.substring(890, 900).trim() : null,
				line.length() >= 935 ? line.substring(900, 935).trim() : null,
				line.length() >= 938 ? line.substring(935, 938).trim() : null,
				line.length() >= 958 ? line.substring(938, 958).trim() : null,
				line.length() >= 966 ? line.substring(958, 966).trim() : null,
				line.length() >= 1065 ? line.substring(966, 1065).trim() : null,
				line.length() >= 1079 ? line.substring(1065, 1079).trim() : null,
				line.length() >= 1089 ? line.substring(1079, 1089).trim() : null,
				line.length() >= 1099 ? line.substring(1089, 1099).trim() : null,
				line.length() >= 1109 ? line.substring(1099, 1109).trim() : null,
				line.length() >= 1119 ? line.substring(1109, 1119).trim() : null,
				line.length() >= 1129 ? line.substring(1119, 1129).trim() : null
				);
	}
	
	public static void insert(B_file_transaction_record rec, PreparedStatement ps_insert_transaction_record, long rec_num) throws SQLException
	{
		ps_insert_transaction_record.setLong(1, rec.getEMPC_file_id());
		ps_insert_transaction_record.setString(2, rec.getMtid());
		ps_insert_transaction_record.setString(3, rec.getRec_centr());
		ps_insert_transaction_record.setString(4, rec.getSend_centr());
		ps_insert_transaction_record.setString(5, rec.getISS_CMI());
		ps_insert_transaction_record.setString(6, rec.getSend_CMI());
		ps_insert_transaction_record.setString(7, rec.getSettl_CMI());
		ps_insert_transaction_record.setString(8, rec.getAcq_bank());
		ps_insert_transaction_record.setString(9, rec.getAcq_branch());
		ps_insert_transaction_record.setString(10, rec.getMember());
		ps_insert_transaction_record.setString(11, rec.getClearing_group());
		ps_insert_transaction_record.setString(12, rec.getMerchant_accept());
		ps_insert_transaction_record.setString(13, rec.getBatch_nr());
		ps_insert_transaction_record.setString(14, rec.getSlip_nr());
		ps_insert_transaction_record.setString(15, rec.getCard());
		ps_insert_transaction_record.setString(16, rec.getExp_date());
		ps_insert_transaction_record.setString(17, rec.getDate());
		ps_insert_transaction_record.setString(18, rec.getTime());
		ps_insert_transaction_record.setString(19, rec.getTran_type());
		ps_insert_transaction_record.setString(20, rec.getAppr_code());
		ps_insert_transaction_record.setString(21, rec.getAppr_src());
		ps_insert_transaction_record.setString(22, rec.getStan());
		ps_insert_transaction_record.setString(23, rec.getRef_number());
		ps_insert_transaction_record.setString(24, rec.getAmount());
		ps_insert_transaction_record.setString(25, rec.getCash_back());
		ps_insert_transaction_record.setString(26, rec.getFee());
		ps_insert_transaction_record.setString(27, rec.getCurrency());
		ps_insert_transaction_record.setString(28, rec.getCcy_exp());
		ps_insert_transaction_record.setString(29, rec.getSb_amount());
		ps_insert_transaction_record.setString(30, rec.getSb_cshback());
		ps_insert_transaction_record.setString(31, rec.getSb_fee());
		ps_insert_transaction_record.setString(32, rec.getSbnk_ccy());
		ps_insert_transaction_record.setString(33, rec.getSb_ccyexp());
		ps_insert_transaction_record.setString(34, rec.getSb_cnvrate());
		ps_insert_transaction_record.setString(35, rec.getSb_cnvdate());
		ps_insert_transaction_record.setString(36, rec.getI_amount());
		ps_insert_transaction_record.setString(37, rec.getI_cshback());
		ps_insert_transaction_record.setString(38, rec.getI_fee());
		ps_insert_transaction_record.setString(39, rec.getIbnk_ccy());
		ps_insert_transaction_record.setString(40, rec.getI_ccyexp());
		ps_insert_transaction_record.setString(41, rec.getI_cnvrate());
		ps_insert_transaction_record.setString(42, rec.getI_cnvdate());
		ps_insert_transaction_record.setString(43, rec.getAbvr_name());
		ps_insert_transaction_record.setString(44, rec.getCity());
		ps_insert_transaction_record.setString(45, rec.getCountry());
		ps_insert_transaction_record.setString(46, rec.getPoint_code());
		ps_insert_transaction_record.setString(47, rec.getMCC_code());
		ps_insert_transaction_record.setString(48, rec.getTerminal());
		ps_insert_transaction_record.setString(49, rec.getBatch_id());
		ps_insert_transaction_record.setString(50, rec.getSettl_nr());
		ps_insert_transaction_record.setString(51, rec.getSettl_date());
		ps_insert_transaction_record.setString(52, rec.getAcqref_nr());
		ps_insert_transaction_record.setString(53, rec.getFile_id());
		ps_insert_transaction_record.setString(54, rec.getMs_number());
		ps_insert_transaction_record.setString(55, rec.getFile_date());
		ps_insert_transaction_record.setString(56, rec.getSource_algorithm());
		ps_insert_transaction_record.setString(57, rec.getTerm_nr());
		ps_insert_transaction_record.setString(58, rec.getECMC_Fee());
		ps_insert_transaction_record.setString(59, rec.getTran_info());
		ps_insert_transaction_record.setString(60, rec.getPr_amount());
		ps_insert_transaction_record.setString(61, rec.getPr_cshback());
		ps_insert_transaction_record.setString(62, rec.getPr_fee());
		ps_insert_transaction_record.setString(63, rec.getPrnk_ccy());
		ps_insert_transaction_record.setString(64, rec.getPr_ccyexp());
		ps_insert_transaction_record.setString(65, rec.getPr_cnvrate());
		ps_insert_transaction_record.setString(66, rec.getPr_cnvdate());
		ps_insert_transaction_record.setString(67, rec.getRegion());
		ps_insert_transaction_record.setString(68, rec.getCard_Type());
		ps_insert_transaction_record.setString(69, rec.getProc_Class());
		ps_insert_transaction_record.setString(70, rec.getCARD_SEQ_NR());
		ps_insert_transaction_record.setString(71, rec.getMsg_type());
		ps_insert_transaction_record.setString(72, rec.getOrg_msg_type());
		ps_insert_transaction_record.setString(73, rec.getProc_code());
		ps_insert_transaction_record.setString(74, rec.getMsg_category());
		ps_insert_transaction_record.setString(75, rec.getMerchant());
		ps_insert_transaction_record.setString(76, rec.getMOTO_IND());
		ps_insert_transaction_record.setString(77, rec.getSusp_status());
		ps_insert_transaction_record.setString(78, rec.getTransact_row());
		ps_insert_transaction_record.setString(79, rec.getAuthoriz_row());
		ps_insert_transaction_record.setString(80, rec.getFLD_043());
		ps_insert_transaction_record.setString(81, rec.getFLD_098());
		ps_insert_transaction_record.setString(82, rec.getFLD_102());
		ps_insert_transaction_record.setString(83, rec.getFLD_103());
		ps_insert_transaction_record.setString(84, rec.getFLD_104());
		ps_insert_transaction_record.setString(85, rec.getFLD_039());
		ps_insert_transaction_record.setString(86, rec.getFLD_SH6());
		ps_insert_transaction_record.setString(87, rec.getBatch_date());
		ps_insert_transaction_record.setString(88, rec.getTr_fee());
		ps_insert_transaction_record.setString(89, rec.getFLD_040());
		ps_insert_transaction_record.setString(90, rec.getFLD_123_1());
		ps_insert_transaction_record.setString(91, rec.getEPI_42_48());
		ps_insert_transaction_record.setString(92, rec.getFLD_003());
		ps_insert_transaction_record.setString(93, rec.getMSC());
		ps_insert_transaction_record.setString(94, rec.getAccount_nr());
		ps_insert_transaction_record.setString(95, rec.getEPI_42_48_FULL());
		ps_insert_transaction_record.setString(96, rec.getOther_Code());
		ps_insert_transaction_record.setString(97, rec.getFLD_015());
		ps_insert_transaction_record.setString(98, rec.getFLD_095());
		ps_insert_transaction_record.setString(99, rec.getAudit_date());
		ps_insert_transaction_record.setString(100, rec.getOther_fee1());
		ps_insert_transaction_record.setString(101, rec.getOther_fee2());
		ps_insert_transaction_record.setString(102, rec.getOther_fee3());
		ps_insert_transaction_record.setString(103, rec.getOther_fee4());
		ps_insert_transaction_record.setString(104, rec.getOther_fee5());
		ps_insert_transaction_record.setLong(105, rec_num);
		ps_insert_transaction_record.addBatch();
	}
	
	public static PreparedStatement get_ps_insert_transaction_record(Connection c) throws SQLException
	{
		return c.prepareStatement(
				"insert into empc_files_b_transactions " +
				"( " +
				"id, " +
				"empc_file_id, " +
				"Mtid, " +
				"Rec_centr, " +
				"Send_centr, " +
				"ISS_CMI, " +
				"Send_CMI, " +
				"Settl_CMI, " +
				"Acq_bank, " +
				"Acq_branch, " +
				"Member, " +
				"Clearing_group, " +
				"Merchant_accept, " +
				"Batch_nr, " +
				"Slip_nr, " +
				"Card, " +
				"Exp_date, " +
				"E_Date, " +
				"E_Time, " +
				"Tran_type, " +
				"Appr_code, " +
				"Appr_src, " +
				"Stan, " +
				"Ref_number, " +
				"Amount, " +
				"Cash_back, " +
				"Fee, " +
				"Currency, " +
				"Ccy_exp, " +
				"Sb_amount, " +
				"Sb_cshback, " +
				"Sb_fee, " +
				"Sbnk_ccy, " +
				"Sb_ccyexp, " +
				"Sb_cnvrate, " +
				"Sb_cnvdate, " +
				"I_amount, " +
				"I_cshback, " +
				"I_fee, " +
				"Ibnk_ccy, " +
				"I_ccyexp, " +
				"I_cnvrate, " +
				"I_cnvdate, " +
				"Abvr_name, " +
				"City, " +
				"Country, " +
				"Point_code, " +
				"MCC_code, " +
				"Terminal, " +
				"Batch_id, " +
				"Settl_nr, " +
				"Settl_date, " +
				"Acqref_nr, " +
				"File_id, " +
				"Ms_number, " +
				"File_date, " +
				"Source_algorithm, " +
				"Term_nr, " +
				"ECMC_Fee, " +
				"Tran_info, " +
				"Pr_amount, " +
				"Pr_cshback, " +
				"Pr_fee, " +
				"Prnk_ccy, " +
				"Pr_ccyexp, " +
				"Pr_cnvrate, " +
				"Pr_cnvdate, " +
				"E_Region, " +
				"Card_Type, " +
				"Proc_Class, " +
				"CARD_SEQ_NR, " +
				"Msg_type, " +
				"Org_msg_type, " +
				"Proc_code, " +
				"Msg_category, " +
				"Merchant, " +
				"MOTO_IND, " +
				"Susp_status, " +
				"Transact_row, " +
				"Authoriz_row, " +
				"FLD_043,  " +
				"FLD_098, " +
				"FLD_102, " +
				"FLD_103, " +
				"FLD_104, " +
				"FLD_039, " +
				"FLD_SH6, " +
				"Batch_date, " +
				"Tr_fee, " +
				"FLD_040, " +
				"FLD_123_1, " +
				"EPI_42_48, " +
				"FLD_003, " +
				"MSC, " +
				"Account_nr, " +
				"EPI_42_48_FULL, " +
				"Other_Code, " +
				"FLD_015, " +
				"FLD_095, " +
				"Audit_date, " +
				"Other_fee1, " +
				"Other_fee2, " +
				"Other_fee3, " +
				"Other_fee4, " +
				"Other_fee5, " +
				"rec_num " +
				") values (" +
				"seq_empc_files_b_trans.nextval," +
				"?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
				"?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
				"?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
				"?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
				"?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
				"?, ?, ?, ?, ?)");
	}
}

