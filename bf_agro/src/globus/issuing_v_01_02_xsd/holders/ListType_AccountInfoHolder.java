/**
 * ListType_AccountInfoHolder.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package globus.issuing_v_01_02_xsd.holders;

public final class ListType_AccountInfoHolder implements javax.xml.rpc.holders.Holder {
    public globus.issuing_v_01_02_xsd.ListType_AccountInfo value;

    public ListType_AccountInfoHolder() {
    }

    public ListType_AccountInfoHolder(globus.issuing_v_01_02_xsd.ListType_AccountInfo value) {
        this.value = value;
    }

	@Override
	public String toString() {
		return "ListType_AccountInfoHolder ["
				+ (value != null ? "value=" + value : "") + "]";
	}

}
