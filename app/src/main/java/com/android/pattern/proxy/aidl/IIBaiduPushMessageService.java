/*
 * This file is auto-generated.  DO NOT MODIFY.
 */
package com.android.pattern.proxy.aidl;
// Declare any non-default types here with import statements
//用来定义百度推送接收和发送消息

public interface IIBaiduPushMessageService extends android.os.IInterface
{
  /** Default implementation for IBaiduPushMessageService. */
  public static class Default implements IIBaiduPushMessageService
  {
    /**
        * 设置要发送的Message的标题
        **/
    @Override public void setBaiduMessageTitle(String msg) throws android.os.RemoteException
    {
    }
    /**
        *获取服务器最近一次推送的Message的标题
        **/
    @Override public String getBaiduMessageTitle() throws android.os.RemoteException
    {
      return null;
    }
    //in表示数据只能从客户端传向服务端,客户端的那个对象不会因为服务器对传参的修改而发生改动
    //out表示服务端接收到的是一个空对象，但是服务端对空对象的修改会同步到客户端
    //inout表示双向流通,服务端接收的是客户端传过来的完整对象，并且服务端的修改会同步到客户端

    @Override public void setBaiduMessage(BaiduMessage msg) throws android.os.RemoteException
    {
    }
    @Override public BaiduMessage getBaiduMessage() throws android.os.RemoteException
    {
      return null;
    }
    @Override
    public android.os.IBinder asBinder() {
      return null;
    }
  }
  /** Local-side IPC implementation stub class. */
  public static abstract class Stub extends android.os.Binder implements IIBaiduPushMessageService
  {
    private static final String DESCRIPTOR = "com.android.pattern.IBaiduPushMessageService";
    /** Construct the stub at attach it to the interface. */
    public Stub()
    {
      this.attachInterface(this, DESCRIPTOR);
    }
    /**
     * Cast an IBinder object into an com.android.pattern.IBaiduPushMessageService interface,
     * generating a proxy if needed.
     */
    public static IIBaiduPushMessageService asInterface(android.os.IBinder obj)
    {
      if ((obj==null)) {
        return null;
      }
      android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
      if (((iin!=null)&&(iin instanceof IIBaiduPushMessageService))) {
        return ((IIBaiduPushMessageService)iin);
      }
      return new Stub.Proxy(obj);
    }
    @Override public android.os.IBinder asBinder()
    {
      return this;
    }
    @Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
    {
      String descriptor = DESCRIPTOR;
      switch (code)
      {
        case INTERFACE_TRANSACTION:
        {
          reply.writeString(descriptor);
          return true;
        }
        case TRANSACTION_setBaiduMessageTitle:
        {
          data.enforceInterface(descriptor);
          String _arg0;
          _arg0 = data.readString();
          this.setBaiduMessageTitle(_arg0);
          reply.writeNoException();
          return true;
        }
        case TRANSACTION_getBaiduMessageTitle:
        {
          data.enforceInterface(descriptor);
          String _result = this.getBaiduMessageTitle();
          reply.writeNoException();
          reply.writeString(_result);
          return true;
        }
        case TRANSACTION_setBaiduMessage:
        {
          data.enforceInterface(descriptor);
          BaiduMessage _arg0;
          if ((0!=data.readInt())) {
            _arg0 = BaiduMessage.CREATOR.createFromParcel(data);
          }
          else {
            _arg0 = null;
          }
          this.setBaiduMessage(_arg0);
          reply.writeNoException();
          return true;
        }
        case TRANSACTION_getBaiduMessage:
        {
          data.enforceInterface(descriptor);
          BaiduMessage _result = this.getBaiduMessage();
          reply.writeNoException();
          if ((_result!=null)) {
            reply.writeInt(1);
            _result.writeToParcel(reply, android.os.Parcelable.PARCELABLE_WRITE_RETURN_VALUE);
          }
          else {
            reply.writeInt(0);
          }
          return true;
        }
        default:
        {
          return super.onTransact(code, data, reply, flags);
        }
      }
    }
    private static class Proxy implements IIBaiduPushMessageService
    {
      private android.os.IBinder mRemote;
      Proxy(android.os.IBinder remote)
      {
        mRemote = remote;
      }
      @Override public android.os.IBinder asBinder()
      {
        return mRemote;
      }
      public String getInterfaceDescriptor()
      {
        return DESCRIPTOR;
      }
      /**
          * 设置要发送的Message的标题
          **/
      @Override public void setBaiduMessageTitle(String msg) throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          _data.writeString(msg);
          boolean _status = mRemote.transact(Stub.TRANSACTION_setBaiduMessageTitle, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            getDefaultImpl().setBaiduMessageTitle(msg);
            return;
          }
          _reply.readException();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
      }
      /**
          *获取服务器最近一次推送的Message的标题
          **/
      @Override public String getBaiduMessageTitle() throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        String _result;
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          boolean _status = mRemote.transact(Stub.TRANSACTION_getBaiduMessageTitle, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            return getDefaultImpl().getBaiduMessageTitle();
          }
          _reply.readException();
          _result = _reply.readString();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
        return _result;
      }
      //in表示数据只能从客户端传向服务端,客户端的那个对象不会因为服务器对传参的修改而发生改动
      //out表示服务端接收到的是一个空对象，但是服务端对空对象的修改会同步到客户端
      //inout表示双向流通,服务端接收的是客户端传过来的完整对象，并且服务端的修改会同步到客户端

      @Override public void setBaiduMessage(BaiduMessage msg) throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          if ((msg!=null)) {
            _data.writeInt(1);
            msg.writeToParcel(_data, 0);
          }
          else {
            _data.writeInt(0);
          }
          boolean _status = mRemote.transact(Stub.TRANSACTION_setBaiduMessage, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            getDefaultImpl().setBaiduMessage(msg);
            return;
          }
          _reply.readException();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
      }
      @Override public BaiduMessage getBaiduMessage() throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        BaiduMessage _result;
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          boolean _status = mRemote.transact(Stub.TRANSACTION_getBaiduMessage, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            return getDefaultImpl().getBaiduMessage();
          }
          _reply.readException();
          if ((0!=_reply.readInt())) {
            _result = BaiduMessage.CREATOR.createFromParcel(_reply);
          }
          else {
            _result = null;
          }
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
        return _result;
      }
      public static IIBaiduPushMessageService sDefaultImpl;
    }
    static final int TRANSACTION_setBaiduMessageTitle = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
    static final int TRANSACTION_getBaiduMessageTitle = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
    static final int TRANSACTION_setBaiduMessage = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
    static final int TRANSACTION_getBaiduMessage = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
    public static boolean setDefaultImpl(IIBaiduPushMessageService impl) {
      if (Stub.Proxy.sDefaultImpl == null && impl != null) {
        Stub.Proxy.sDefaultImpl = impl;
        return true;
      }
      return false;
    }
    public static IIBaiduPushMessageService getDefaultImpl() {
      return Stub.Proxy.sDefaultImpl;
    }
  }
  /**
      * 设置要发送的Message的标题
      **/
  public void setBaiduMessageTitle(String msg) throws android.os.RemoteException;
  /**
      *获取服务器最近一次推送的Message的标题
      **/
  public String getBaiduMessageTitle() throws android.os.RemoteException;
  //in表示数据只能从客户端传向服务端,客户端的那个对象不会因为服务器对传参的修改而发生改动
  //out表示服务端接收到的是一个空对象，但是服务端对空对象的修改会同步到客户端
  //inout表示双向流通,服务端接收的是客户端传过来的完整对象，并且服务端的修改会同步到客户端

  public void setBaiduMessage(BaiduMessage msg) throws android.os.RemoteException;
  public BaiduMessage getBaiduMessage() throws android.os.RemoteException;
}
