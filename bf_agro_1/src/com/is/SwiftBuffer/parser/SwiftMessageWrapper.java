package com.is.SwiftBuffer.parser;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.prowidesoftware.swift.model.field.Field32A;

import net.sourceforge.wife.swift.model.SwiftBlock1;
import net.sourceforge.wife.swift.model.SwiftBlock2;
import net.sourceforge.wife.swift.model.SwiftBlock2Input;
import net.sourceforge.wife.swift.model.SwiftBlock2Output;
import net.sourceforge.wife.swift.model.SwiftBlock4;
import net.sourceforge.wife.swift.model.SwiftMessage;

public class SwiftMessageWrapper implements Serializable 
{
  private static final long serialVersionUID = 1L;
  
  @SuppressWarnings("unused")
  private static transient final java.util.logging.Logger log = java.util.logging.Logger.getLogger(SwiftMessageWrapper.class.getName());
  
  private SwiftMessage message;
  private String messageText;
  private String messageType;
  private String applicationId;
  private String serviceId;
  private String bicFrom;
  private String bicTo;
  private String direction;
  private String countryFrom;
  private String countryTo;
  private Calendar valueDate;
  private Double amount;
  private String currency;
  private String reference;
  private String operationCode;
  private String orderParty;
  private String benParty;
  private String narrative;
  private String order_party_acc;
  private String ben_party_acc;
  private String detailsOfCharges;
  
  
  public String getBicFrom()
{
	return bicFrom;
}

public void setBicFrom(String bicFrom)
{
	this.bicFrom = bicFrom;
}

public String getBicTo()
{
	return bicTo;
}

public void setBicTo(String bicTo)
{
	this.bicTo = bicTo;
}

public String getOrder_party_acc()
{
	return order_party_acc;
}

public void setOrder_party_acc(String order_party_acc)
{
	this.order_party_acc = order_party_acc;
}

public String getBen_party_acc()
{
	return ben_party_acc;
}

public void setBen_party_acc(String ben_party_acc)
{
	this.ben_party_acc = ben_party_acc;
}

public String getDetailsOfCharges()
{
	return detailsOfCharges;
}

public void setDetailsOfCharges(String detailsOfCharges)
{
	this.detailsOfCharges = detailsOfCharges;
}

public SwiftMessageWrapper()
  {
    super();
  }
  
  public SwiftMessageWrapper(SwiftMessage msg, String text)
  {
    super();
    setMessage(msg);
    setMessageText(text);
    
    processMessageInfo();
  }
  
  @SuppressWarnings("deprecation")
  private void processMessageInfo()
  {
    if (message == null)
    {
      return;
    }
    
    messageType = message.getType();
    if (messageType == null)
    {
      return;
    }
    SwiftBlock1 block1 = message.getBlock1();
    if (block1 == null)
    {
      return;
    }
    applicationId = block1.getApplicationId();
    serviceId = block1.getServiceId();
    
    SwiftBlock2 block2 = message.getBlock2();
    if (block2 != null)
    {
      direction = block2.isInput() ? "I" : "O";
      if (direction.equalsIgnoreCase("I"))
      {
        SwiftBlock2Input block2In = (SwiftBlock2Input)block2;
        
        if (block2In != null)
        {
          bicFrom = block1.getLogicalTerminal();
          bicTo = block2In.getReceiverAddress();
          countryFrom = bicFrom.substring(4, 6);
          countryTo = bicTo.substring(4, 6);
        }
      }
      else
      {
        SwiftBlock2Output block2Out = (SwiftBlock2Output)block2;
        
        if (block2Out != null)
        {
          bicFrom = block2Out.getMIRLogicalTerminal();
          bicTo = block1.getLogicalTerminal();
          countryFrom = bicFrom.substring(4, 6);
          countryTo = bicTo.substring(4, 6);
        }
      }
    }
    
    if (messageType.equalsIgnoreCase("103"))
    {
      SwiftBlock4 block4 = message.getBlock4();
      if (block4 != null)
      {
        String field32AStr = block4.getTagValue("32A");
        if (field32AStr != null)
        {
          Field32A field32A = new Field32A(field32AStr);
          if (field32A != null)
          {
            amount = field32A.getComponent3AsNumber().doubleValue();
            currency = field32A.getCurrency();
            valueDate = field32A.getComponent1AsCalendar();
          }
        }
        
        reference = block4.getTagValue("20");       
        operationCode = block4.getTagValue("23B");    
        orderParty = block4.getTagValue("50A");
        if (orderParty == null)
        {
          orderParty = block4.getTagValue("50K");
        }
        if (orderParty == null)
        {
          orderParty = block4.getTagValue("50F");
        }
        benParty = block4.getTagValue("59A");
        if (benParty == null)
        {
          benParty = block4.getTagValue("59");
        }
        narrative = block4.getTagValue("70");
        
        this.detailsOfCharges = block4.getTagValue("71A");
        this.order_party_acc = block4.getTagValue("50K");
        this.ben_party_acc = block4.getTagValue("59");
      }
    }
  }
  
  public String getCsvText(String delimeter)
  {
    StringBuilder result = new StringBuilder();
    
    result.append(messageType);
    result.append(delimeter);
    result.append(applicationId);
    result.append(delimeter);
    result.append(serviceId);
    result.append(delimeter);
    result.append(bicFrom);
    result.append(delimeter);
    result.append(bicTo);
    result.append(delimeter);
    result.append(direction);
    result.append(delimeter);
    result.append(countryFrom);
    result.append(delimeter);
    result.append(countryTo);
    result.append(delimeter);
    result.append(getFormattedDate());
    result.append(delimeter);
    result.append(amount);
    result.append(delimeter);
    result.append(currency);
    result.append(delimeter);
    result.append(reference);
    result.append(delimeter);
    result.append(operationCode);
    result.append(delimeter);
    result.append(orderParty);
    result.append(delimeter);
    result.append(benParty);
    result.append(delimeter);
    result.append(narrative);
    result.append(delimeter);
    result.append(messageText);
    
    return result.toString();
  }
  
  public String getXmlText(boolean needsHeader)
  {
    StringBuilder result = new StringBuilder();
    
    if (needsHeader)
    {
      result.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
    }
    
    result.append("<swiftMsg>\n");
    
    result.append("<id></id>\n");
    result.append("<insertDate>");
    result.append(getFormattedCurrentDate());
    result.append("</insertDate>\n");
    result.append("<state>0</state>\n");
    
    result.append("<messageType>");
    if (messageType != null) result.append(messageType.trim());
    result.append("</messageType>\n");
    
    result.append("<applicationId>");
    if (applicationId != null) result.append(applicationId.trim());
    result.append("</applicationId>\n");
    
    result.append("<serviceId>");
    if (serviceId != null) result.append(serviceId.trim());
    result.append("</serviceId>\n");
    
    result.append("<bicFrom>");
    if (bicFrom != null) result.append(bicFrom.trim());
    result.append("</bicFrom>\n");
    
    result.append("<bicTo>");
    if (bicTo != null) result.append(bicTo.trim());
    result.append("</bicTo>\n");
    
    result.append("<direction>");
    if (direction != null) result.append(direction.trim());
    result.append("</direction>\n");
    
    result.append("<countryFrom>");
    if (countryFrom != null) result.append(countryFrom.trim());
    result.append("</countryFrom>\n");
    
    result.append("<countryTo>");
    if (countryTo != null) result.append(countryTo.trim());
    result.append("</countryTo>\n");
    
    result.append("<valueDate>");
    if (valueDate != null) result.append(getFormattedDate());
    result.append("</valueDate>\n");
    
    result.append("<amount>");
    if (amount != null) result.append(getFormattedAmount());
    result.append("</amount>\n");
    
    result.append("<currency>");
    if (currency != null) result.append(currency.trim());
    result.append("</currency>\n");
    
    result.append("<reference>");
    if (reference != null) result.append(reference.trim());
    result.append("</reference>\n");
    
    result.append("<operationCode>");
    if (operationCode != null) result.append(operationCode.trim());
    result.append("</operationCode>\n");
    
    result.append("<orderParty>");
    if (orderParty != null) result.append(orderParty.trim());
    result.append("</orderParty>\n");
    
    result.append("<benParty>");
    if (benParty != null) result.append(benParty.trim());
    result.append("</benParty>\n");
    
    result.append("<narrative>");
    if (narrative != null) result.append(narrative.trim());
    result.append("</narrative>\n");
    
    result.append("<messageText>");
    if (messageText != null) result.append(messageText.trim());
    result.append("</messageText>\n");
    
    result.append("</swiftMsg>\n");
    
    return result.toString();
  }
  
  public String getFormattedDate()
  {
    SimpleDateFormat dateformatter = new SimpleDateFormat("dd.MM.yyyy");
    return dateformatter.format(valueDate.getTime());    
  }
  
  public String getFormattedCurrentDate()
  {
    SimpleDateFormat dateformatter = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");
    return dateformatter.format(Calendar.getInstance().getTime());    
  }
  
  public String getFormattedAmount()
  {
    DecimalFormat formatter = new DecimalFormat("#.#");
    return formatter.format(amount);    
  }

  public void setMessage(SwiftMessage message)
  {
    this.message = message;
  }

  public SwiftMessage getMessage()
  {
    return message;
  }

  public void setMessageText(String messageText)
  {
    this.messageText = messageText;
  }

  public String getMessageText()
  {
    return messageText;
  }

  public void setMessageType(String messageType)
  {
    this.messageType = messageType;
  }

  public String getMessageType()
  {
    return messageType;
  }

  public void setCountryFrom(String countryFrom)
  {
    this.countryFrom = countryFrom;
  }

  public String getCountryFrom()
  {
    return countryFrom;
  }

  public void setCountryTo(String countryTo)
  {
    this.countryTo = countryTo;
  }

  public String getCountryTo()
  {
    return countryTo;
  }

  public void setApplicationId(String applicationId)
  {
    this.applicationId = applicationId;
  }

  public String getApplicationId()
  {
    return applicationId;
  }

  public void setServiceId(String serviceId)
  {
    this.serviceId = serviceId;
  }

  public String getServiceId()
  {
    return serviceId;
  }

  public void setDirection(String direction)
  {
    this.direction = direction;
  }

  public String getDirection()
  {
    return direction;
  }

  public void setValueDate(Calendar valueDate)
  {
    this.valueDate = valueDate;
  }

  public Calendar getValueDate()
  {
    return valueDate;
  }

  public void setAmount(Double amount)
  {
    this.amount = amount;
  }

  public Double getAmount()
  {
    return amount;
  }

  public void setCurrency(String currency)
  {
    this.currency = currency;
  }

  public String getCurrency()
  {
    return currency;
  }

  public void setReference(String reference)
  {
    this.reference = reference;
  }

  public String getReference()
  {
    return reference;
  }

  public void setOperationCode(String operationCode)
  {
    this.operationCode = operationCode;
  }

  public String getOperationCode()
  {
    return operationCode;
  }

  public void setOrderParty(String orderParty)
  {
    this.orderParty = orderParty;
  }

  public String getOrderParty()
  {
    return orderParty;
  }

  public void setBenParty(String benParty)
  {
    this.benParty = benParty;
  }

  public String getBenParty()
  {
    return benParty;
  }

  public void setNarrative(String narrative)
  {
    this.narrative = narrative;
  }

  public String getNarrative()
  {
    return narrative;
  }
  
}
