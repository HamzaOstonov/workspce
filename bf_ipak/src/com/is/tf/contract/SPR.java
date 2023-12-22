package com.is.tf.contract;

import com.is.utils.CheckNull;

public class SPR
{
	public static String getP100Value(String data) throws Exception
	{
		if (data.equals("0")) data = "��������";
		if (data.equals("1")) data = "������";
		if (data.equals("2")) data = "� ��������";
		if (data.equals("3")) data = "��������������";
		if (data.equals("4")) data = "��������";
		if (data.equals("5")) data = "������*";
		if (data.equals("7")) data = "� �������� ��.";
		if (data.equals("8")) data = "� �������� ���.";
		if (data.equals("9")) data = "� �������� ��.����";
		if (data.equals("b")) data = "�������� ��.����";
		if (data.equals("u")) data = "�������� ���.";
		if (data.equals("g")) data = "�������� ��.";
		
		return data;
	}
	
	public static String getOsnovanie(String data) throws Exception
	{
		if (data.equals("1")) data = "������ ��������������� ������";
		if (data.equals("2")) data = "������ �����������";
		if (data.equals("3")) data = "������ �������� ������������ �����";
		if (data.equals("4")) data = "������ ���������� ������";
		if (data.equals("5")) data = "�� �������� �����������";
		if (data.equals("6")) data = "���������� ������";
		return data;
	}
	
	public static String getIstochn_sredstv(String data) throws Exception
	{
		if (data.equals("1")) data = "C����������";
		if (data.equals("2")) data = "���������";
		if (data.equals("3")) data = "�������";
		return data;
	}
	
	public static String getTransferType(String data) throws Exception
	{
		if (data.equals("1")) data = "� ������ �������������� ����";
		if (data.equals("2")) data = "�� �������-������� �����(�������)";
		return data;
	}
	
	public static String getPenaltyType(String data) throws Exception
	{
		if (data.equals("1")) data = "���������� ������";
		if (data.equals("2")) data = "���������� ������";
		return data;
	}
	
	public static String getConditions(String data) throws Exception
	{
		if (data.equals("1")) data = "����������";
		if (data.equals("2")) data = "�� �����������";
		if (data.equals("3")) data = "�� ����� ������ ���������� ��������";
		if (data.equals("4")) data = "�� ����� ������ ���������� ������";
		if (data.equals("5")) data = "�� ����� (�����������)";
		if (data.equals("6")) data = "�� �������� �������";
		if (data.equals("7")) data = "�������";
		if (data.equals("8")) data = "������ �� ����� �������";
		if (data.equals("9")) data = "������ �� �����";
		if (data.equals("10")) data = "���������� ������ �������� ������������ �����";
		return data;
	}
	
	public static String getVal(String data) throws Exception
	{
		if (data.equals("000")) data = "��� (��� ��������.�������� ��� 860) ";
		if (data.equals("001")) data = "����������� ����� ������������� ";
		if (data.equals("004")) data = "������ ";
		if (data.equals("008")) data = "��� ";
		if (data.equals("012")) data = "����p���� ����p ";
		if (data.equals("020")) data = "����p���� ������ ";
		if (data.equals("024")) data = "������ ";
		if (data.equals("031")) data = "���p����������� ����� ";
		if (data.equals("032")) data = "�p���������� ���� ";
		if (data.equals("036")) data = "����p�������� �����p ";
		if (data.equals("040")) data = "������� ";
		if (data.equals("044")) data = "��������� �����p ";
		if (data.equals("048")) data = "���p������� ����p ";
		if (data.equals("050")) data = "���� ";
		if (data.equals("051")) data = "��������� ���� ";
		if (data.equals("052")) data = "��p��������� �����p ";
		if (data.equals("056")) data = "����������� �p��� ";
		if (data.equals("060")) data = "��p������� �����p ";
		if (data.equals("064")) data = "�����p�� ";
		if (data.equals("068")) data = "��������� ";
		if (data.equals("070")) data = " ";
		if (data.equals("072")) data = "���� ";
		if (data.equals("084")) data = "��������� ������ ";
		if (data.equals("090")) data = "�����p ����������� ���p���� ";
		if (data.equals("096")) data = "�p�������� �����p ";
		if (data.equals("100")) data = "��� ���������� ";
		if (data.equals("104")) data = "���� ";
		if (data.equals("108")) data = "��p��������� ����� ";
		if (data.equals("112")) data = "����p������ p���� ";
		if (data.equals("116")) data = "����� ";
		if (data.equals("124")) data = "��������� �����p ";
		if (data.equals("132")) data = "������ ����-��p�� ";
		if (data.equals("136")) data = "�����p �������� ������ ";
		if (data.equals("144")) data = "�p�-���������� ����� ";
		if (data.equals("152")) data = "��������� ���� ";
		if (data.equals("156")) data = "���� �������� ";
		if (data.equals("170")) data = "������������ ���� ";
		if (data.equals("174")) data = "�p��� ����� ";
		if (data.equals("180")) data = "���p ";
		if (data.equals("188")) data = "�����p�������� ����� ";
		if (data.equals("191")) data = "���������� ���� ";
		if (data.equals("192")) data = "��������� ���� ";
		if (data.equals("196")) data = "���p���� ���� ";
		if (data.equals("203")) data = "������� �p��� ";
		if (data.equals("208")) data = "������� �p��� ";
		if (data.equals("214")) data = "������������� ���� ";
		if (data.equals("218")) data = "����������� ���p� ";
		if (data.equals("222")) data = "��������p���� ����� ";
		if (data.equals("230")) data = "��������� ��� ";
		if (data.equals("232")) data = "����� ";
		if (data.equals("233")) data = "�p��� ";
		if (data.equals("238")) data = "���� ������������ ���p���� ";
		if (data.equals("242")) data = "�����p ����� ";
		if (data.equals("246")) data = "������� ��p�� ";
		if (data.equals("250")) data = "�p��������� �p��� ";
		if (data.equals("262")) data = "�p��� ������� ";
		if (data.equals("268")) data = "�p�������� ���� ";
		if (data.equals("270")) data = "������ ";
		if (data.equals("276")) data = "�������� ����� ";
		if (data.equals("280")) data = "�������� ��p�� ";
		if (data.equals("288")) data = "������� ��� ";
		if (data.equals("292")) data = "���p����p���� ���� ";
		if (data.equals("300")) data = "��������� �p���� ";
		if (data.equals("320")) data = "������� ";
		if (data.equals("324")) data = "���������� �p��� ";
		if (data.equals("328")) data = "��������� �����p ";
		if (data.equals("332")) data = "��p� ";
		if (data.equals("340")) data = "�����p� ";
		if (data.equals("344")) data = "����������� �����p ";
		if (data.equals("348")) data = "��p��� ";
		if (data.equals("352")) data = "���������� �p��� ";
		if (data.equals("356")) data = "��������� p���� ";
		if (data.equals("360")) data = "����� ";
		if (data.equals("364")) data = "�p������ p��� ";
		if (data.equals("368")) data = "�p������ ����p ";
		if (data.equals("372")) data = "�p�������� ���� ";
		if (data.equals("376")) data = "����� ����������� ������ ";
		if (data.equals("380")) data = "����������� ��p� ";
		if (data.equals("388")) data = "�������� �����p ";
		if (data.equals("392")) data = "���� ";
		if (data.equals("398")) data = "����� ";
		if (data.equals("400")) data = "��p������� ����p ";
		if (data.equals("404")) data = "��������� ������� ";
		if (data.equals("408")) data = "����p�-��p������ ���� ";
		if (data.equals("410")) data = "���� ";
		if (data.equals("414")) data = "���������� ����p ";
		if (data.equals("417")) data = "��� ";
		if (data.equals("418")) data = "��� ";
		if (data.equals("422")) data = "��������� ���� ";
		if (data.equals("426")) data = "���� ";
		if (data.equals("428")) data = "���������� ��� ";
		if (data.equals("430")) data = "����p������ �����p ";
		if (data.equals("434")) data = "��������� ����p ";
		if (data.equals("440")) data = "��������� ��� ";
		if (data.equals("442")) data = "��������p����� �p��� ";
		if (data.equals("446")) data = "������ ";
		if (data.equals("450")) data = "���������p���� �p��� ";
		if (data.equals("454")) data = "����� ";
		if (data.equals("458")) data = "������������ p������ ";
		if (data.equals("462")) data = "����� ";
		if (data.equals("470")) data = "����������� ��p� ";
		if (data.equals("478")) data = "���� ";
		if (data.equals("480")) data = "���p�������� p���� ";
		if (data.equals("484")) data = "������������ ���� ";
		if (data.equals("496")) data = "����������� ���p�� ";
		if (data.equals("498")) data = "���������� ��� ";
		if (data.equals("504")) data = "��p��������� ��p��� ";
		if (data.equals("508")) data = "�������� ";
		if (data.equals("512")) data = "�������� p��� ";
		if (data.equals("516")) data = "������ ������� ";
		if (data.equals("524")) data = "���������� p���� ";
		if (data.equals("528")) data = "����p�������� ������� ";
		if (data.equals("532")) data = "����p�������� ���������� ������� ";
		if (data.equals("533")) data = "�p�������� ������� ";
		if (data.equals("548")) data = "���� ";
		if (data.equals("554")) data = "�������������� �����p ";
		if (data.equals("558")) data = "������� ��p���� ";
		if (data.equals("566")) data = "���p� ";
		if (data.equals("578")) data = "��p������� �p��� ";
		if (data.equals("586")) data = "������������ p���� ";
		if (data.equals("590")) data = "������� ";
		if (data.equals("591")) data = "������� ";
		if (data.equals("598")) data = "���� ";
		if (data.equals("600")) data = "���p��� ";
		if (data.equals("604")) data = "����� ���� ";
		if (data.equals("608")) data = "������������ ���� ";
		if (data.equals("620")) data = "��p���������� ������ ";
		if (data.equals("624")) data = "���� ������-����� ";
		if (data.equals("626")) data = "����p���� ������ ";
		if (data.equals("634")) data = "����p���� p��� ";
		if (data.equals("642")) data = "��������� ��� ";
		if (data.equals("643")) data = "���������� ����� ";
		if (data.equals("646")) data = "�p��� ������ ";
		if (data.equals("654")) data = "���� ������ ����� ";
		if (data.equals("678")) data = "���p� ";
		if (data.equals("682")) data = "���������� ���� ";
		if (data.equals("690")) data = "����������� p���� ";
		if (data.equals("694")) data = "����� ";
		if (data.equals("702")) data = "�������p���� �����p ";
		if (data.equals("703")) data = "��������� ����� ";
		if (data.equals("704")) data = "���� ";
		if (data.equals("705")) data = "���������� ����p ";
		if (data.equals("706")) data = "����������� ������� ";
		if (data.equals("710")) data = "���� ";
		if (data.equals("716")) data = "�����p �������� ";
		if (data.equals("724")) data = "��������� ������ ";
		if (data.equals("736")) data = "��������� ���� ";
		if (data.equals("740")) data = "��p�������� ������� ";
		if (data.equals("748")) data = "��������� ";
		if (data.equals("752")) data = "�������� �p��� ";
		if (data.equals("756")) data = "������p���� �p��� ";
		if (data.equals("760")) data = "��p������ ���� ";
		if (data.equals("762")) data = "���������� ���� ";
		if (data.equals("764")) data = "��� ";
		if (data.equals("776")) data = "������ ";
		if (data.equals("780")) data = "�����p �p������� � ������ ";
		if (data.equals("784")) data = "��p��� ��� ";
		if (data.equals("788")) data = "��������� ����p ";
		if (data.equals("792")) data = "��p����� ��p� ";
		if (data.equals("795")) data = "����� ";
		if (data.equals("800")) data = "����������� ������� ";
		if (data.equals("807")) data = "����p ";
		if (data.equals("810")) data = "���������� p���� ";
		if (data.equals("818")) data = "���������� ���� ";
		if (data.equals("826")) data = "���� ���p������ ";
		if (data.equals("834")) data = "������������ ������� ";
		if (data.equals("840")) data = "�����p ��� ";
		if (data.equals("858")) data = "�p��������� ���� ";
		if (data.equals("860")) data = "��� ";
		if (data.equals("862")) data = "������p ";
		if (data.equals("882")) data = "���� ";
		if (data.equals("886")) data = "��������� ���� ";
		if (data.equals("890")) data = "����������� ����p ";
		if (data.equals("894")) data = "����� ";
		if (data.equals("901")) data = "����� ����������� ������ ";
		if (data.equals("932")) data = "������ �������� ";
		if (data.equals("934")) data = "����� ����� ";
		if (data.equals("936")) data = "���� ���� ";
		if (data.equals("937")) data = "������� Fuerte ";
		if (data.equals("938")) data = "��������� ���� ";
		if (data.equals("940")) data = "����������� ���� ";
		if (data.equals("941")) data = "�������� ����� ";
		if (data.equals("943")) data = "������� ";
		if (data.equals("944")) data = "��������������� ����� ";
		if (data.equals("946")) data = "����� ��������� ��� ";
		if (data.equals("949")) data = "����� �������� ���� ";
		if (data.equals("950")) data = "�p��� ��� ���� ";
		if (data.equals("951")) data = "��������-��p������ ������ ";
		if (data.equals("952")) data = "�p��� ��� ����� ";
		if (data.equals("953")) data = "�p��� ��� ";
		if (data.equals("954")) data = "��� ";
		if (data.equals("959")) data = "������ ";
		if (data.equals("960")) data = "����������� ����� ������������� ";
		if (data.equals("961")) data = "������� ";
		if (data.equals("962")) data = "������� ";
		if (data.equals("968")) data = "����������� ������ ";
		if (data.equals("969")) data = "������������� ������ ";
		if (data.equals("971")) data = "������ ";
		if (data.equals("972")) data = "������ ";
		if (data.equals("973")) data = "������ ";
		if (data.equals("974")) data = "����������� ����� ";
		if (data.equals("975")) data = "���������� ��� ";
		if (data.equals("976")) data = "������������ ����� ";
		if (data.equals("977")) data = "�������������� ����� ";
		if (data.equals("978")) data = "���� ";
		if (data.equals("980")) data = "������ ";
		if (data.equals("981")) data = "���� ";
		if (data.equals("985")) data = "������ ";
		if (data.equals("986")) data = "����������� ���� ";
		if (CheckNull.isEmpty(data) || data.equals("null")) data = "�� ������a";
		
		return data;
	}
}
