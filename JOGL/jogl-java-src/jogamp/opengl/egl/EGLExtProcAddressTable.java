/* !---- DO NOT EDIT: This file autogenerated by com/jogamp/gluegen/opengl/GLEmitter.java on Sat Oct 10 03:19:33 CEST 2015 ----! */

package jogamp.opengl.egl;

import java.util.*;
import com.jogamp.opengl.*;
import com.jogamp.opengl.fixedfunc.*;
import jogamp.opengl.*;
import com.jogamp.opengl.egl.EGLExt;
import com.jogamp.opengl.egl.EGLClientPixmapHI;
import com.jogamp.gluegen.runtime.ProcAddressTable;
import com.jogamp.common.util.SecurityUtil;

/**
 * This table is a cache of pointers to the dynamically-linkable C library.
 * @see ProcAddressTable
 */
/* pp */ final class EGLExtProcAddressTable extends ProcAddressTable {


  public EGLExtProcAddressTable(){ super(); }

  public EGLExtProcAddressTable(com.jogamp.gluegen.runtime.FunctionAddressResolver resolver){ super(resolver); }

  /* pp */ long _addressof_eglChooseConfig;
  /* pp */ long _addressof_eglCopyBuffers;
  /* pp */ long _addressof_eglCreateContext;
  /* pp */ long _addressof_eglCreatePbufferSurface;
  /* pp */ long _addressof_eglCreatePixmapSurface;
  /* pp */ long _addressof_eglCreateWindowSurface;
  /* pp */ long _addressof_eglDestroyContext;
  /* pp */ long _addressof_eglDestroySurface;
  /* pp */ long _addressof_eglGetConfigAttrib;
  /* pp */ long _addressof_eglGetConfigs;
  /* pp */ long _addressof_eglGetCurrentDisplay;
  /* pp */ long _addressof_eglGetCurrentSurface;
  /* pp */ long _addressof_eglGetDisplay;
  /* pp */ long _addressof_eglGetError;
  /* pp */ long _addressof_eglInitialize;
  /* pp */ long _addressof_eglMakeCurrent;
  /* pp */ long _addressof_eglQueryContext;
  /* pp */ long _addressof_eglQueryString;
  /* pp */ long _addressof_eglQuerySurface;
  /* pp */ long _addressof_eglSwapBuffers;
  /* pp */ long _addressof_eglTerminate;
  /* pp */ long _addressof_eglWaitGL;
  /* pp */ long _addressof_eglWaitNative;
  /* pp */ long _addressof_eglBindTexImage;
  /* pp */ long _addressof_eglReleaseTexImage;
  /* pp */ long _addressof_eglSurfaceAttrib;
  /* pp */ long _addressof_eglSwapInterval;
  /* pp */ long _addressof_eglBindAPI;
  /* pp */ long _addressof_eglQueryAPI;
  /* pp */ long _addressof_eglCreatePbufferFromClientBuffer;
  /* pp */ long _addressof_eglReleaseThread;
  /* pp */ long _addressof_eglWaitClient;
  /* pp */ long _addressof_eglGetCurrentContext;
  /* pp */ long _addressof_eglCreateSync;
  /* pp */ long _addressof_eglDestroySync;
  /* pp */ long _addressof_eglClientWaitSync;
  /* pp */ long _addressof_eglGetSyncAttrib;
  /* pp */ long _addressof_eglCreateImage;
  /* pp */ long _addressof_eglDestroyImage;
  /* pp */ long _addressof_eglGetPlatformDisplay;
  /* pp */ long _addressof_eglCreatePlatformWindowSurface;
  /* pp */ long _addressof_eglCreatePlatformPixmapSurface;
  /* pp */ long _addressof_eglWaitSync;
  /* pp */ long _addressof_eglCreateSync64KHR;
  /* pp */ long _addressof_eglQueryDebugKHR;
  /* pp */ long _addressof_eglLabelObjectKHR;
  /* pp */ long _addressof_eglCreateSyncKHR;
  /* pp */ long _addressof_eglDestroySyncKHR;
  /* pp */ long _addressof_eglClientWaitSyncKHR;
  /* pp */ long _addressof_eglGetSyncAttribKHR;
  /* pp */ long _addressof_eglCreateImageKHR;
  /* pp */ long _addressof_eglDestroyImageKHR;
  /* pp */ long _addressof_eglLockSurfaceKHR;
  /* pp */ long _addressof_eglUnlockSurfaceKHR;
  /* pp */ long _addressof_eglQuerySurface64KHR;
  /* pp */ long _addressof_eglSetDamageRegionKHR;
  /* pp */ long _addressof_eglSignalSyncKHR;
  /* pp */ long _addressof_eglCreateStreamKHR;
  /* pp */ long _addressof_eglDestroyStreamKHR;
  /* pp */ long _addressof_eglStreamAttribKHR;
  /* pp */ long _addressof_eglQueryStreamKHR;
  /* pp */ long _addressof_eglQueryStreamu64KHR;
  /* pp */ long _addressof_eglStreamConsumerGLTextureExternalKHR;
  /* pp */ long _addressof_eglStreamConsumerAcquireKHR;
  /* pp */ long _addressof_eglStreamConsumerReleaseKHR;
  /* pp */ long _addressof_eglGetStreamFileDescriptorKHR;
  /* pp */ long _addressof_eglCreateStreamFromFileDescriptorKHR;
  /* pp */ long _addressof_eglQueryStreamTimeKHR;
  /* pp */ long _addressof_eglCreateStreamProducerSurfaceKHR;
  /* pp */ long _addressof_eglSwapBuffersWithDamageKHR;
  /* pp */ long _addressof_eglWaitSyncKHR;
  /* pp */ long _addressof_eglDupNativeFenceFDANDROID;
  /* pp */ long _addressof_eglQuerySurfacePointerANGLE;
  /* pp */ long _addressof_eglQueryDeviceAttribEXT;
  /* pp */ long _addressof_eglQueryDeviceStringEXT;
  /* pp */ long _addressof_eglQueryDevicesEXT;
  /* pp */ long _addressof_eglQueryDisplayAttribEXT;
  /* pp */ long _addressof_eglGetOutputLayersEXT;
  /* pp */ long _addressof_eglGetOutputPortsEXT;
  /* pp */ long _addressof_eglOutputLayerAttribEXT;
  /* pp */ long _addressof_eglQueryOutputLayerAttribEXT;
  /* pp */ long _addressof_eglQueryOutputLayerStringEXT;
  /* pp */ long _addressof_eglOutputPortAttribEXT;
  /* pp */ long _addressof_eglQueryOutputPortAttribEXT;
  /* pp */ long _addressof_eglQueryOutputPortStringEXT;
  /* pp */ long _addressof_eglGetPlatformDisplayEXT;
  /* pp */ long _addressof_eglCreatePlatformWindowSurfaceEXT;
  /* pp */ long _addressof_eglCreatePlatformPixmapSurfaceEXT;
  /* pp */ long _addressof_eglStreamConsumerOutputEXT;
  /* pp */ long _addressof_eglSwapBuffersWithDamageEXT;
  /* pp */ long _addressof_eglCreatePixmapSurfaceHI;
  /* pp */ long _addressof_eglCreateDRMImageMESA;
  /* pp */ long _addressof_eglExportDRMImageMESA;
  /* pp */ long _addressof_eglExportDMABUFImageQueryMESA;
  /* pp */ long _addressof_eglExportDMABUFImageMESA;
  /* pp */ long _addressof_eglSwapBuffersRegionNOK;
  /* pp */ long _addressof_eglSwapBuffersRegion2NOK;
  /* pp */ long _addressof_eglQueryNativeDisplayNV;
  /* pp */ long _addressof_eglQueryNativeWindowNV;
  /* pp */ long _addressof_eglQueryNativePixmapNV;
  /* pp */ long _addressof_eglPostSubBufferNV;
  /* pp */ long _addressof_eglCreateStreamSyncNV;
  /* pp */ long _addressof_eglCreateFenceSyncNV;
  /* pp */ long _addressof_eglDestroySyncNV;
  /* pp */ long _addressof_eglFenceNV;
  /* pp */ long _addressof_eglClientWaitSyncNV;
  /* pp */ long _addressof_eglSignalSyncNV;
  /* pp */ long _addressof_eglGetSyncAttribNV;
  /* pp */ long _addressof_eglGetSystemTimeFrequencyNV;
  /* pp */ long _addressof_eglGetSystemTimeNV;
  @Override
  protected boolean isFunctionAvailableImpl(String functionNameUsr) throws IllegalArgumentException  {
    final String functionNameBase = com.jogamp.gluegen.runtime.opengl.GLNameResolver.normalizeVEN(com.jogamp.gluegen.runtime.opengl.GLNameResolver.normalizeARB(functionNameUsr, true), true);
    final String addressFieldNameBase = "_addressof_" + functionNameBase;
    final int funcNamePermNum = com.jogamp.gluegen.runtime.opengl.GLNameResolver.getFuncNamePermutationNumber(functionNameBase);
    final java.lang.reflect.Field addressField = java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<java.lang.reflect.Field>() {
        public final java.lang.reflect.Field run() {
            java.lang.reflect.Field addressField = null;
            for(int i = 0; i < funcNamePermNum; i++) {
                final String addressFieldName = com.jogamp.gluegen.runtime.opengl.GLNameResolver.getFuncNamePermutation(addressFieldNameBase, i);
                try {
                    addressField = EGLExtProcAddressTable.class.getDeclaredField( addressFieldName );
                    addressField.setAccessible(true); // we need to read the protected value!
                    return addressField;
                } catch (NoSuchFieldException ex) { }
            }
            return null;
        } } );

    if(null==addressField) {
      // The user is calling a bogus function or one which is not
      // runtime linked
      throw new RuntimeException(
          "WARNING: Address field query failed for \"" + functionNameBase + "\"/\"" + functionNameUsr +
          "\"; it's either statically linked or address field is not a known " +
          "function");
    } 
    try {
      return 0 != addressField.getLong(this);
    } catch (Exception e) {
      throw new RuntimeException(
          "WARNING: Address query failed for \"" + functionNameBase + "\"/\"" + functionNameUsr +
          "\"; it's either statically linked or is not a known " +
          "function", e);
    }
  }
  @Override
  public long getAddressFor(String functionNameUsr) throws SecurityException, IllegalArgumentException {
    SecurityUtil.checkAllLinkPermission();
    final String functionNameBase = com.jogamp.gluegen.runtime.opengl.GLNameResolver.normalizeVEN(com.jogamp.gluegen.runtime.opengl.GLNameResolver.normalizeARB(functionNameUsr, true), true);
    final String addressFieldNameBase = "_addressof_" + functionNameBase;
    final int  funcNamePermNum = com.jogamp.gluegen.runtime.opengl.GLNameResolver.getFuncNamePermutationNumber(functionNameBase);
    final java.lang.reflect.Field addressField = java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<java.lang.reflect.Field>() {
        public final java.lang.reflect.Field run() {
            java.lang.reflect.Field addressField = null;
            for(int i = 0; i < funcNamePermNum; i++) {
                final String addressFieldName = com.jogamp.gluegen.runtime.opengl.GLNameResolver.getFuncNamePermutation(addressFieldNameBase, i);
                try {
                    addressField = EGLExtProcAddressTable.class.getDeclaredField( addressFieldName );
                    addressField.setAccessible(true); // we need to read the protected value!
                    return addressField;
                } catch (NoSuchFieldException ex) { }
            }
            return null;
        } } );

    if(null==addressField) {
      // The user is calling a bogus function or one which is not
      // runtime linked
      throw new RuntimeException(
          "WARNING: Address field query failed for \"" + functionNameBase + "\"/\"" + functionNameUsr +
          "\"; it's either statically linked or address field is not a known " +
          "function");
    } 
    try {
      return addressField.getLong(this);
    } catch (Exception e) {
      throw new RuntimeException(
          "WARNING: Address query failed for \"" + functionNameBase + "\"/\"" + functionNameUsr +
          "\"; it's either statically linked or is not a known " +
          "function", e);
    }
  }
} // end of class EGLExtProcAddressTable
