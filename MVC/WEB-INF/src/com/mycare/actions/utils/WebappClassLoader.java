/*      */ package com.mycare.actions.utils;
/*      */ 
/*      */ import java.beans.Introspector;
/*      */ import java.io.ByteArrayInputStream;
/*      */ import java.io.File;
/*      */ import java.io.FileOutputStream;
/*      */ import java.io.FilePermission;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.lang.ref.Reference;
/*      */ import java.lang.ref.WeakReference;
/*      */ import java.lang.reflect.Field;
/*      */ import java.lang.reflect.Method;
/*      */ import java.lang.reflect.Modifier;
/*      */ import java.net.MalformedURLException;
/*      */ import java.net.URI;
/*      */ import java.net.URL;
/*      */ import java.net.URLClassLoader;
/*      */ import java.nio.charset.Charset;
/*      */ import java.security.AccessControlException;
/*      */ import java.security.AccessController;
/*      */ import java.security.AllPermission;
/*      */ import java.security.CodeSource;
/*      */ import java.security.Permission;
/*      */ import java.security.PermissionCollection;
/*      */ import java.security.Policy;
/*      */ import java.security.PrivilegedAction;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collection;
/*      */ import java.util.Date;
/*      */ import java.util.Enumeration;
/*      */ import java.util.HashMap;
/*      */ import java.util.Iterator;
/*      */ import java.util.LinkedHashMap;
/*      */ import java.util.LinkedHashSet;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Map.Entry;
/*      */ import java.util.ResourceBundle;
/*      */ import java.util.Set;
/*      */ import java.util.concurrent.ThreadPoolExecutor;
/*      */ import java.util.jar.Attributes;
/*      */ import java.util.jar.Attributes.Name;
/*      */ import java.util.jar.JarEntry;
/*      */ import java.util.jar.JarFile;
/*      */ import java.util.jar.Manifest;
/*      */ import javax.naming.NameClassPair;
/*      */ import javax.naming.NamingEnumeration;
/*      */ import javax.naming.NamingException;
/*      */ import javax.naming.directory.DirContext;
/*      */ import org.apache.catalina.Globals;
/*      */ import org.apache.catalina.Lifecycle;
/*      */ import org.apache.catalina.LifecycleException;
/*      */ import org.apache.catalina.LifecycleListener;
/*      */ import org.apache.catalina.LifecycleState;
/*      */ import org.apache.juli.logging.Log;
/*      */ import org.apache.juli.logging.LogFactory;
/*      */ import org.apache.naming.JndiPermission;
/*      */ import org.apache.naming.resources.ProxyDirContext;
/*      */ import org.apache.naming.resources.Resource;
/*      */ import org.apache.naming.resources.ResourceAttributes;
/*      */ import org.apache.tomcat.util.ExceptionUtils;
/*      */ import org.apache.tomcat.util.IntrospectionUtils;
/*      */ import org.apache.tomcat.util.res.StringManager;
/*      */ 
/*      */ public class WebappClassLoader extends URLClassLoader
/*      */   implements Lifecycle
/*      */ {
/*  127 */   private static final Log log = LogFactory.getLog(WebappClassLoader.class);
/*      */ 
/*  130 */   private static final Charset CHARSET_UTF8 = Charset.forName("UTF-8");
/*      */ 
/*  135 */   private static final List<String> JVM_THREAD_GROUP_NAMES = new ArrayList();
/*      */   private static final String JVN_THREAD_GROUP_SYSTEM = "system";
/*      */   protected static final String[] triggers;
/*      */   protected static final String[] packageTriggers;
/*      */   protected static final StringManager sm;
/*  215 */   boolean antiJARLocking = false;
/*      */ 
/*  266 */   protected DirContext resources = null;
/*      */ 
/*  273 */   protected HashMap<String, ResourceEntry> resourceEntries = new HashMap();
/*      */ 
/*  279 */   protected HashMap<String, String> notFoundResources = new LinkedHashMap()
/*      */   {
/*      */     private static final long serialVersionUID = 1L;
/*      */ 
/*      */     protected boolean removeEldestEntry(Map.Entry<String, String> eldest) {
/*  285 */       return size() > 1000;
/*      */     }
/*  279 */   };
/*      */ 
/*  299 */   protected boolean delegate = false;
/*      */ 
/*  305 */   protected long lastJarAccessed = 0L;
/*      */ 
/*  312 */   protected String[] repositories = new String[0];
/*      */ 
/*  318 */   protected URL[] repositoryURLs = null;
/*      */ 
/*  326 */   protected File[] files = new File[0];
/*      */ 
/*  333 */   protected JarFile[] jarFiles = new JarFile[0];
/*      */ 
/*  340 */   protected File[] jarRealFiles = new File[0];
/*      */ 
/*  346 */   protected String jarPath = null;
/*      */ 
/*  353 */   protected String[] jarNames = new String[0];
/*      */ 
/*  360 */   protected long[] lastModifiedDates = new long[0];
/*      */ 
/*  367 */   protected String[] paths = new String[0];
/*      */ 
/*  374 */   protected ArrayList<Permission> permissionList = new ArrayList();
/*      */ 
/*  381 */   protected File loaderDir = null;
/*  382 */   protected String canonicalLoaderDir = null;
/*      */ 
/*  388 */   protected HashMap<String, PermissionCollection> loaderPC = new HashMap();
/*      */ 
/*  394 */   protected SecurityManager securityManager = null;
/*      */ 
/*  400 */   protected ClassLoader parent = null;
/*      */ 
/*  406 */   protected ClassLoader system = null;
/*      */ 
/*  412 */   protected boolean started = false;
/*      */ 
/*  418 */   protected boolean hasExternalRepositories = false;
/*      */ 
/*  423 */   protected boolean searchExternalFirst = false;
/*      */ 
/*  428 */   protected boolean needConvert = false;
/*      */ 
/*  434 */   protected Permission allPermission = new AllPermission();
/*      */ 
/*  446 */   private boolean clearReferencesStatic = false;
/*      */ 
/*  457 */   private boolean clearReferencesStopThreads = false;
/*      */ 
/*  464 */   private boolean clearReferencesStopTimerThreads = false;
/*      */ 
/*  472 */   private boolean clearReferencesLogFactoryRelease = true;
/*      */ 
/*  482 */   private boolean clearReferencesHttpClientKeepAliveThread = true;
/*      */ 
/*  490 */   private String contextName = "unknown";
/*      */ 
/*      */   public WebappClassLoader()
/*      */   {
/*  226 */     super(new URL[0]);
/*  227 */     this.parent = getParent();
/*  228 */     this.system = getSystemClassLoader();
/*  229 */     this.securityManager = System.getSecurityManager();
/*      */ 
/*  231 */     if (this.securityManager != null)
/*  232 */       refreshPolicy();
/*      */   }
/*      */ 
/*      */   public WebappClassLoader(ClassLoader parent)
/*      */   {
/*  246 */     super(new URL[0], parent);
/*      */ 
/*  248 */     this.parent = getParent();
/*      */ 
/*  250 */     this.system = getSystemClassLoader();
/*  251 */     this.securityManager = System.getSecurityManager();
/*      */ 
/*  253 */     if (this.securityManager != null)
/*  254 */       refreshPolicy();
/*      */   }
/*      */ 
/*      */   public DirContext getResources()
/*      */   {
/*  501 */     return this.resources;
/*      */   }
/*      */ 
/*      */   public void setResources(DirContext resources)
/*      */   {
/*  511 */     this.resources = resources;
/*      */ 
/*  513 */     if ((resources instanceof ProxyDirContext))
/*  514 */       this.contextName = ((ProxyDirContext)resources).getContextName();
/*      */   }
/*      */ 
/*      */   public String getContextName()
/*      */   {
/*  524 */     return this.contextName;
/*      */   }
/*      */ 
/*      */   public boolean getDelegate()
/*      */   {
/*  534 */     return this.delegate;
/*      */   }
/*      */ 
/*      */   public void setDelegate(boolean delegate)
/*      */   {
/*  554 */     this.delegate = delegate;
/*      */   }
/*      */ 
/*      */   public boolean getAntiJARLocking()
/*      */   {
/*  563 */     return this.antiJARLocking;
/*      */   }
/*      */ 
/*      */   public void setAntiJARLocking(boolean antiJARLocking)
/*      */   {
/*  571 */     this.antiJARLocking = antiJARLocking;
/*      */   }
/*      */ 
/*      */   public boolean getSearchExternalFirst()
/*      */   {
/*  578 */     return this.searchExternalFirst;
/*      */   }
/*      */ 
/*      */   public void setSearchExternalFirst(boolean searchExternalFirst)
/*      */   {
/*  585 */     this.searchExternalFirst = searchExternalFirst;
/*      */   }
/*      */ 
/*      */   public void addPermission(String filepath)
/*      */   {
/*  596 */     if (filepath == null) {
/*  597 */       return;
/*      */     }
/*      */ 
/*  600 */     String path = filepath;
/*      */ 
/*  602 */     if (this.securityManager != null) {
/*  603 */       Permission permission = null;
/*  604 */       if ((path.startsWith("jndi:")) || (path.startsWith("jar:jndi:"))) {
/*  605 */         if (!path.endsWith("/")) {
/*  606 */           path = new StringBuilder().append(path).append("/").toString();
/*      */         }
/*  608 */         permission = new JndiPermission(new StringBuilder().append(path).append("*").toString());
/*  609 */         addPermission(permission);
/*      */       } else {
/*  611 */         if (!path.endsWith(File.separator)) {
/*  612 */           permission = new FilePermission(path, "read");
/*  613 */           addPermission(permission);
/*  614 */           path = new StringBuilder().append(path).append(File.separator).toString();
/*      */         }
/*  616 */         permission = new FilePermission(new StringBuilder().append(path).append("-").toString(), "read");
/*  617 */         addPermission(permission);
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   public void addPermission(URL url)
/*      */   {
/*  630 */     if (url != null)
/*  631 */       addPermission(url.toString());
/*      */   }
/*      */ 
/*      */   public void addPermission(Permission permission)
/*      */   {
/*  642 */     if ((this.securityManager != null) && (permission != null))
/*  643 */       this.permissionList.add(permission);
/*      */   }
/*      */ 
/*      */   public String getJarPath()
/*      */   {
/*  653 */     return this.jarPath;
/*      */   }
/*      */ 
/*      */   public void setJarPath(String jarPath)
/*      */   {
/*  663 */     this.jarPath = jarPath;
/*      */   }
/*      */ 
/*      */   public void setWorkDir(File workDir)
/*      */   {
/*  672 */     this.loaderDir = new File(workDir, "loader");
/*  673 */     if (this.loaderDir == null)
/*  674 */       this.canonicalLoaderDir = null;
/*      */     else
/*      */       try {
/*  677 */         this.canonicalLoaderDir = this.loaderDir.getCanonicalPath();
/*  678 */         if (!this.canonicalLoaderDir.endsWith(File.separator))
/*  679 */           this.canonicalLoaderDir = new StringBuilder().append(this.canonicalLoaderDir).append(File.separator).toString();
/*      */       }
/*      */       catch (IOException ioe) {
/*  682 */         this.canonicalLoaderDir = null;
/*      */       }
/*      */   }
/*      */ 
/*      */   protected void setParentClassLoader(ClassLoader pcl)
/*      */   {
/*  692 */     this.parent = pcl;
/*      */   }
/*      */ 
/*      */   public boolean getClearReferencesStatic()
/*      */   {
/*  699 */     return this.clearReferencesStatic;
/*      */   }
/*      */ 
/*      */   public void setClearReferencesStatic(boolean clearReferencesStatic)
/*      */   {
/*  709 */     this.clearReferencesStatic = clearReferencesStatic;
/*      */   }
/*      */ 
/*      */   public boolean getClearReferencesStopThreads()
/*      */   {
/*  717 */     return this.clearReferencesStopThreads;
/*      */   }
/*      */ 
/*      */   public void setClearReferencesStopThreads(boolean clearReferencesStopThreads)
/*      */   {
/*  728 */     this.clearReferencesStopThreads = clearReferencesStopThreads;
/*      */   }
/*      */ 
/*      */   public boolean getClearReferencesStopTimerThreads()
/*      */   {
/*  736 */     return this.clearReferencesStopTimerThreads;
/*      */   }
/*      */ 
/*      */   public void setClearReferencesStopTimerThreads(boolean clearReferencesStopTimerThreads)
/*      */   {
/*  747 */     this.clearReferencesStopTimerThreads = clearReferencesStopTimerThreads;
/*      */   }
/*      */ 
/*      */   public boolean getClearReferencesLogFactoryRelease()
/*      */   {
/*  755 */     return this.clearReferencesLogFactoryRelease;
/*      */   }
/*      */ 
/*      */   public void setClearReferencesLogFactoryRelease(boolean clearReferencesLogFactoryRelease)
/*      */   {
/*  766 */     this.clearReferencesLogFactoryRelease = clearReferencesLogFactoryRelease;
/*      */   }
/*      */ 
/*      */   public boolean getClearReferencesHttpClientKeepAliveThread()
/*      */   {
/*  776 */     return this.clearReferencesHttpClientKeepAliveThread;
/*      */   }
/*      */ 
/*      */   public void setClearReferencesHttpClientKeepAliveThread(boolean clearReferencesHttpClientKeepAliveThread)
/*      */   {
/*  788 */     this.clearReferencesHttpClientKeepAliveThread = clearReferencesHttpClientKeepAliveThread;
/*      */   }
/*      */ 
/*      */   public void addRepository(String repository)
/*      */   {
/*  810 */     if ((repository.startsWith("/WEB-INF/lib")) || (repository.startsWith("/WEB-INF/classes")))
/*      */     {
/*  812 */       return;
/*      */     }
/*      */     try
/*      */     {
/*  816 */       URL url = new URL(repository);
/*  817 */       super.addURL(url);
/*  818 */       this.hasExternalRepositories = true;
/*  819 */       this.repositoryURLs = null;
/*      */     } catch (MalformedURLException e) {
/*  821 */       IllegalArgumentException iae = new IllegalArgumentException(new StringBuilder().append("Invalid repository: ").append(repository).toString());
/*      */ 
/*  823 */       iae.initCause(e);
/*  824 */       throw iae;
/*      */     }
/*      */   }
/*      */ 
/*      */   synchronized void addRepository(String repository, File file)
/*      */   {
/*  845 */     if (repository == null) {
/*  846 */       return;
/*      */     }
/*  848 */     if (log.isDebugEnabled()) {
/*  849 */       log.debug(new StringBuilder().append("addRepository(").append(repository).append(")").toString());
/*      */     }
/*      */ 
/*  854 */     String[] result = new String[this.repositories.length + 1];
/*  855 */     for (int i = 0; i < this.repositories.length; i++) {
/*  856 */       result[i] = this.repositories[i];
/*      */     }
/*  858 */     result[this.repositories.length] = repository;
/*  859 */     this.repositories = result;
/*      */ 
/*  862 */     File[] result2 = new File[this.files.length + 1];
/*  863 */     for (i = 0; i < this.files.length; i++) {
/*  864 */       result2[i] = this.files[i];
/*      */     }
/*  866 */     result2[this.files.length] = file;
/*  867 */     this.files = result2;
/*      */   }
/*      */ 
/*      */   synchronized void addJar(String jar, JarFile jarFile, File file)
/*      */     throws IOException
/*      */   {
/*  875 */     if (jar == null)
/*  876 */       return;
/*  877 */     if (jarFile == null)
/*  878 */       return;
/*  879 */     if (file == null) {
/*  880 */       return;
/*      */     }
/*  882 */     if (log.isDebugEnabled()) {
/*  883 */       log.debug(new StringBuilder().append("addJar(").append(jar).append(")").toString());
/*      */     }
/*      */ 
/*  887 */     if ((this.jarPath != null) && (jar.startsWith(this.jarPath)))
/*      */     {
/*  889 */       String jarName = jar.substring(this.jarPath.length());
/*  890 */       while (jarName.startsWith("/")) {
/*  891 */         jarName = jarName.substring(1);
/*      */       }
/*  893 */       String[] result = new String[this.jarNames.length + 1];
/*  894 */       for (int i = 0; i < this.jarNames.length; i++) {
/*  895 */         result[i] = this.jarNames[i];
/*      */       }
/*  897 */       result[this.jarNames.length] = jarName;
/*  898 */       this.jarNames = result;
/*      */     }
/*      */ 
/*      */     try
/*      */     {
/*  906 */       long lastModified = ((ResourceAttributes)this.resources.getAttributes(jar)).getLastModified();
/*      */ 
/*  910 */       String[] result = new String[this.paths.length + 1];
/*  911 */       for (i = 0; i < this.paths.length; i++) {
/*  912 */         result[i] = this.paths[i];
/*      */       }
/*  914 */       result[this.paths.length] = jar;
/*  915 */       this.paths = result;
/*      */ 
/*  917 */       long[] result3 = new long[this.lastModifiedDates.length + 1];
/*  918 */       for (i = 0; i < this.lastModifiedDates.length; i++) {
/*  919 */         result3[i] = this.lastModifiedDates[i];
/*      */       }
/*  921 */       result3[this.lastModifiedDates.length] = lastModified;
/*  922 */       this.lastModifiedDates = result3;
/*      */     }
/*      */     catch (NamingException e)
/*      */     {
/*      */     }
/*      */ 
/*  930 */     if (!validateJarFile(file)) {
/*  931 */       return;
/*      */     }
/*  933 */     JarFile[] result2 = new JarFile[this.jarFiles.length + 1];
/*  934 */     for (int i = 0; i < this.jarFiles.length; i++) {
/*  935 */       result2[i] = this.jarFiles[i];
/*      */     }
/*  937 */     result2[this.jarFiles.length] = jarFile;
/*  938 */     this.jarFiles = result2;
/*      */ 
/*  941 */     File[] result4 = new File[this.jarRealFiles.length + 1];
/*  942 */     for (i = 0; i < this.jarRealFiles.length; i++) {
/*  943 */       result4[i] = this.jarRealFiles[i];
/*      */     }
/*  945 */     result4[this.jarRealFiles.length] = file;
/*  946 */     this.jarRealFiles = result4;
/*      */   }
/*      */ 
/*      */   public String[] findRepositories()
/*      */   {
/*  958 */     return (String[])this.repositories.clone();
/*      */   }
/*      */ 
/*      */   public boolean modified()
/*      */   {
/*  969 */     if (log.isDebugEnabled()) {
/*  970 */       log.debug("modified()");
/*      */     }
/*      */ 
/*  973 */     int length = this.paths.length;
/*      */ 
/*  978 */     int length2 = this.lastModifiedDates.length;
/*  979 */     if (length > length2) {
/*  980 */       length = length2;
/*      */     }
/*  982 */     for (int i = 0; i < length; i++) {
/*      */       try {
/*  984 */         long lastModified = ((ResourceAttributes)this.resources.getAttributes(this.paths[i])).getLastModified();
/*      */ 
/*  987 */         if (lastModified != this.lastModifiedDates[i]) {
/*  988 */           if (log.isDebugEnabled()) {
/*  989 */             log.debug(new StringBuilder().append("  Resource '").append(this.paths[i]).append("' was modified; Date is now: ").append(new Date(lastModified)).append(" Was: ").append(new Date(this.lastModifiedDates[i])).toString());
/*      */           }
/*      */ 
/*  993 */           return true;
/*      */         }
/*      */       } catch (NamingException e) {
/*  996 */         log.error(new StringBuilder().append("    Resource '").append(this.paths[i]).append("' is missing").toString());
/*  997 */         return true;
/*      */       }
/*      */     }
/*      */ 
/* 1001 */     length = this.jarNames.length;
/*      */ 
/* 1004 */     if (getJarPath() != null) {
/*      */       try
/*      */       {
/* 1007 */         NamingEnumeration enumeration = this.resources.listBindings(getJarPath());
/*      */ 
/* 1009 */         int i = 0;
/* 1010 */         while ((enumeration.hasMoreElements()) && (i < length)) {
/* 1011 */           NameClassPair ncPair = (NameClassPair)enumeration.nextElement();
/* 1012 */           String name = ncPair.getName();
/*      */ 
/* 1014 */           if (!name.endsWith(".jar"))
/*      */             continue;
/* 1016 */           if (!name.equals(this.jarNames[i]))
/*      */           {
/* 1018 */             log.info(new StringBuilder().append("    Additional JARs have been added : '").append(name).append("'").toString());
/*      */ 
/* 1020 */             return true;
/*      */           }
/* 1022 */           i++;
/*      */         }
/* 1024 */         if (enumeration.hasMoreElements())
/* 1025 */           while (enumeration.hasMoreElements()) {
/* 1026 */             NameClassPair ncPair = (NameClassPair)enumeration.nextElement();
/* 1027 */             String name = ncPair.getName();
/*      */ 
/* 1029 */             if (name.endsWith(".jar"))
/*      */             {
/* 1031 */               log.info("    Additional JARs have been added");
/* 1032 */               return true;
/*      */             }
/*      */           }
/* 1035 */         if (i < this.jarNames.length)
/*      */         {
/* 1037 */           log.info("    Additional JARs have been added");
/* 1038 */           return true;
/*      */         }
/*      */       } catch (NamingException e) {
/* 1041 */         if (log.isDebugEnabled())
/* 1042 */           log.debug(new StringBuilder().append("    Failed tracking modifications of '").append(getJarPath()).append("'").toString());
/*      */       }
/*      */       catch (ClassCastException e) {
/* 1045 */         log.error(new StringBuilder().append("    Failed tracking modifications of '").append(getJarPath()).append("' : ").append(e.getMessage()).toString());
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/* 1052 */     return false;
/*      */   }
/*      */ 
/*      */   public String toString()
/*      */   {
/* 1063 */     StringBuilder sb = new StringBuilder("WebappClassLoader\r\n");
/* 1064 */     sb.append("  context: ");
/* 1065 */     sb.append(this.contextName);
/* 1066 */     sb.append("\r\n");
/* 1067 */     sb.append("  delegate: ");
/* 1068 */     sb.append(this.delegate);
/* 1069 */     sb.append("\r\n");
/* 1070 */     sb.append("  repositories:\r\n");
/* 1071 */     if (this.repositories != null) {
/* 1072 */       for (int i = 0; i < this.repositories.length; i++) {
/* 1073 */         sb.append("    ");
/* 1074 */         sb.append(this.repositories[i]);
/* 1075 */         sb.append("\r\n");
/*      */       }
/*      */     }
/* 1078 */     if (this.parent != null) {
/* 1079 */       sb.append("----------> Parent Classloader:\r\n");
/* 1080 */       sb.append(this.parent.toString());
/* 1081 */       sb.append("\r\n");
/*      */     }
/* 1083 */     return sb.toString();
/*      */   }
/*      */ 
/*      */   protected void addURL(URL url)
/*      */   {
/* 1096 */     super.addURL(url);
/* 1097 */     this.hasExternalRepositories = true;
/* 1098 */     this.repositoryURLs = null;
/*      */   }
/*      */ 
/*      */   public Class<?> findClass(String name)
/*      */     throws ClassNotFoundException
/*      */   {
/* 1113 */     if (log.isDebugEnabled()) {
/* 1114 */       log.debug(new StringBuilder().append("    findClass(").append(name).append(")").toString());
/*      */     }
/*      */ 
/* 1117 */     if (!this.started) {
/* 1118 */       throw new ClassNotFoundException(name);
/*      */     }
/*      */ 
/* 1122 */     if (this.securityManager != null) {
/* 1123 */       int i = name.lastIndexOf(46);
/* 1124 */       if (i >= 0) {
/*      */         try {
/* 1126 */           if (log.isTraceEnabled())
/* 1127 */             log.trace("      securityManager.checkPackageDefinition");
/* 1128 */           this.securityManager.checkPackageDefinition(name.substring(0, i));
/*      */         } catch (Exception se) {
/* 1130 */           if (log.isTraceEnabled())
/* 1131 */             log.trace("      -->Exception-->ClassNotFoundException", se);
/* 1132 */           throw new ClassNotFoundException(name, se);
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/* 1139 */     Class clazz = null;
/*      */     try {
/* 1141 */       if (log.isTraceEnabled())
/* 1142 */         log.trace(new StringBuilder().append("      findClassInternal(").append(name).append(")").toString());
/* 1143 */       if ((this.hasExternalRepositories) && (this.searchExternalFirst)) {
/*      */         try {
/* 1145 */           clazz = super.findClass(name);
/*      */         } catch (ClassNotFoundException cnfe) {
/*      */         }
/*      */         catch (AccessControlException ace) {
/* 1149 */           log.warn(new StringBuilder().append("WebappClassLoader.findClassInternal(").append(name).append(") security exception: ").append(ace.getMessage()).toString(), ace);
/*      */ 
/* 1151 */           throw new ClassNotFoundException(name, ace);
/*      */         } catch (RuntimeException e) {
/* 1153 */           if (log.isTraceEnabled())
/* 1154 */             log.trace("      -->RuntimeException Rethrown", e);
/* 1155 */           throw e;
/*      */         }
/*      */       }
/* 1158 */       if (clazz == null) {
/*      */         try {
/* 1160 */           clazz = findClassInternal(name);
/*      */         } catch (ClassNotFoundException cnfe) {
/* 1162 */           if ((!this.hasExternalRepositories) || (this.searchExternalFirst))
/* 1163 */             throw cnfe;
/*      */         }
/*      */         catch (AccessControlException ace) {
/* 1166 */           log.warn(new StringBuilder().append("WebappClassLoader.findClassInternal(").append(name).append(") security exception: ").append(ace.getMessage()).toString(), ace);
/*      */ 
/* 1168 */           throw new ClassNotFoundException(name, ace);
/*      */         } catch (RuntimeException e) {
/* 1170 */           if (log.isTraceEnabled())
/* 1171 */             log.trace("      -->RuntimeException Rethrown", e);
/* 1172 */           throw e;
/*      */         }
/*      */       }
/* 1175 */       if ((clazz == null) && (this.hasExternalRepositories) && (!this.searchExternalFirst)) {
/*      */         try {
/* 1177 */           clazz = super.findClass(name);
/*      */         } catch (AccessControlException ace) {
/* 1179 */           log.warn(new StringBuilder().append("WebappClassLoader.findClassInternal(").append(name).append(") security exception: ").append(ace.getMessage()).toString(), ace);
/*      */ 
/* 1181 */           throw new ClassNotFoundException(name, ace);
/*      */         } catch (RuntimeException e) {
/* 1183 */           if (log.isTraceEnabled())
/* 1184 */             log.trace("      -->RuntimeException Rethrown", e);
/* 1185 */           throw e;
/*      */         }
/*      */       }
/* 1188 */       if (clazz == null) {
/* 1189 */         if (log.isDebugEnabled())
/* 1190 */           log.debug("    --> Returning ClassNotFoundException");
/* 1191 */         throw new ClassNotFoundException(name);
/*      */       }
/*      */     } catch (ClassNotFoundException e) {
/* 1194 */       if (log.isTraceEnabled())
/* 1195 */         log.trace("    --> Passing on ClassNotFoundException");
/* 1196 */       throw e;
/*      */     }
/*      */ 
/* 1200 */     if (log.isTraceEnabled()) {
/* 1201 */       log.debug(new StringBuilder().append("      Returning class ").append(clazz).toString());
/*      */     }
/* 1203 */     if (log.isTraceEnabled())
/*      */     {
/*      */       ClassLoader cl;
/*      */       ClassLoader cl;
/* 1205 */       if (Globals.IS_SECURITY_ENABLED) {
/* 1206 */         cl = (ClassLoader)AccessController.doPrivileged(new PrivilegedGetClassLoader(clazz));
/*      */       }
/*      */       else {
/* 1209 */         cl = clazz.getClassLoader();
/*      */       }
/* 1211 */       log.debug(new StringBuilder().append("      Loaded by ").append(cl.toString()).toString());
/*      */     }
/* 1213 */     return clazz;
/*      */   }
/*      */ 
/*      */   public URL findResource(String name)
/*      */   {
/* 1228 */     if (log.isDebugEnabled()) {
/* 1229 */       log.debug(new StringBuilder().append("    findResource(").append(name).append(")").toString());
/*      */     }
/* 1231 */     URL url = null;
/*      */ 
/* 1233 */     if ((this.hasExternalRepositories) && (this.searchExternalFirst)) {
/* 1234 */       url = super.findResource(name);
/*      */     }
/* 1236 */     if (url == null) {
/* 1237 */       ResourceEntry entry = (ResourceEntry)this.resourceEntries.get(name);
/* 1238 */       if (entry == null) {
/* 1239 */         if (this.securityManager != null) {
/* 1240 */           PrivilegedAction dp = new PrivilegedFindResourceByName(name, name);
/*      */ 
/* 1242 */           entry = (ResourceEntry)AccessController.doPrivileged(dp);
/*      */         } else {
/* 1244 */           entry = findResourceInternal(name, name);
/*      */         }
/*      */       }
/* 1247 */       if (entry != null) {
/* 1248 */         url = entry.source;
/*      */       }
/*      */     }
/*      */ 
/* 1252 */     if ((url == null) && (this.hasExternalRepositories) && (!this.searchExternalFirst)) {
/* 1253 */       url = super.findResource(name);
/*      */     }
/* 1255 */     if (log.isDebugEnabled()) {
/* 1256 */       if (url != null)
/* 1257 */         log.debug(new StringBuilder().append("    --> Returning '").append(url.toString()).append("'").toString());
/*      */       else
/* 1259 */         log.debug("    --> Resource not found, returning null");
/*      */     }
/* 1261 */     return url;
/*      */   }
/*      */ 
/*      */   public Enumeration<URL> findResources(String name)
/*      */     throws IOException
/*      */   {
/* 1278 */     if (log.isDebugEnabled()) {
/* 1279 */       log.debug(new StringBuilder().append("    findResources(").append(name).append(")").toString());
/*      */     }
/*      */ 
/* 1282 */     LinkedHashSet result = new LinkedHashSet();
/*      */ 
/* 1284 */     int jarFilesLength = this.jarFiles.length;
/* 1285 */     int repositoriesLength = this.repositories.length;
/*      */ 
/* 1290 */     if ((this.hasExternalRepositories) && (this.searchExternalFirst))
/*      */     {
/* 1292 */       Enumeration otherResourcePaths = super.findResources(name);
/*      */ 
/* 1294 */       while (otherResourcePaths.hasMoreElements()) {
/* 1295 */         result.add(otherResourcePaths.nextElement());
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/* 1300 */     for (int i = 0; i < repositoriesLength; i++) {
/*      */       try {
/* 1302 */         String fullPath = new StringBuilder().append(this.repositories[i]).append(name).toString();
/* 1303 */         this.resources.lookup(fullPath);
/*      */         try
/*      */         {
/* 1307 */           result.add(getURI(new File(this.files[i], name)));
/*      */         }
/*      */         catch (MalformedURLException e)
/*      */         {
/*      */         }
/*      */       }
/*      */       catch (NamingException e)
/*      */       {
/*      */       }
/*      */     }
/* 1317 */     synchronized (this.jarFiles) {
/* 1318 */       if (openJARs()) {
/* 1319 */         for (i = 0; i < jarFilesLength; i++) {
/* 1320 */           JarEntry jarEntry = this.jarFiles[i].getJarEntry(name);
/* 1321 */           if (jarEntry == null) continue;
/*      */           try {
/* 1323 */             String jarFakeUrl = getURI(this.jarRealFiles[i]).toString();
/* 1324 */             jarFakeUrl = new StringBuilder().append("jar:").append(jarFakeUrl).append("!/").append(name).toString();
/* 1325 */             result.add(new URL(jarFakeUrl));
/*      */           }
/*      */           catch (MalformedURLException e)
/*      */           {
/*      */           }
/*      */         }
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/* 1335 */     if ((this.hasExternalRepositories) && (!this.searchExternalFirst))
/*      */     {
/* 1337 */       Enumeration otherResourcePaths = super.findResources(name);
/*      */ 
/* 1339 */       while (otherResourcePaths.hasMoreElements()) {
/* 1340 */         result.add(otherResourcePaths.nextElement());
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/* 1345 */     Iterator iterator = result.iterator();
/*      */ 
/* 1347 */     return new Enumeration(iterator)
/*      */     {
/*      */       public boolean hasMoreElements() {
/* 1350 */         return this.val$iterator.hasNext();
/*      */       }
/*      */ 
/*      */       public URL nextElement()
/*      */       {
/* 1355 */         return (URL)this.val$iterator.next();
/*      */       }
/*      */     };
/*      */   }
/*      */ 
/*      */   public URL getResource(String name)
/*      */   {
/* 1387 */     if (log.isDebugEnabled())
/* 1388 */       log.debug(new StringBuilder().append("getResource(").append(name).append(")").toString());
/* 1389 */     URL url = null;
/*      */ 
/* 1392 */     if (this.delegate) {
/* 1393 */       if (log.isDebugEnabled())
/* 1394 */         log.debug(new StringBuilder().append("  Delegating to parent classloader ").append(this.parent).toString());
/* 1395 */       ClassLoader loader = this.parent;
/* 1396 */       if (loader == null)
/* 1397 */         loader = this.system;
/* 1398 */       url = loader.getResource(name);
/* 1399 */       if (url != null) {
/* 1400 */         if (log.isDebugEnabled())
/* 1401 */           log.debug(new StringBuilder().append("  --> Returning '").append(url.toString()).append("'").toString());
/* 1402 */         return url;
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/* 1407 */     url = findResource(name);
/* 1408 */     if (url != null)
/*      */     {
/* 1411 */       if (this.antiJARLocking) {
/* 1412 */         ResourceEntry entry = (ResourceEntry)this.resourceEntries.get(name);
/*      */         try {
/* 1414 */           String repository = entry.codeBase.toString();
/* 1415 */           if ((repository.endsWith(".jar")) && (!name.endsWith(".class")))
/*      */           {
/* 1418 */             File resourceFile = new File(this.loaderDir, name);
/* 1419 */             url = getURI(resourceFile);
/*      */           }
/*      */         }
/*      */         catch (Exception e) {
/*      */         }
/*      */       }
/* 1425 */       if (log.isDebugEnabled())
/* 1426 */         log.debug(new StringBuilder().append("  --> Returning '").append(url.toString()).append("'").toString());
/* 1427 */       return url;
/*      */     }
/*      */ 
/* 1431 */     if (!this.delegate) {
/* 1432 */       ClassLoader loader = this.parent;
/* 1433 */       if (loader == null)
/* 1434 */         loader = this.system;
/* 1435 */       url = loader.getResource(name);
/* 1436 */       if (url != null) {
/* 1437 */         if (log.isDebugEnabled())
/* 1438 */           log.debug(new StringBuilder().append("  --> Returning '").append(url.toString()).append("'").toString());
/* 1439 */         return url;
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/* 1444 */     if (log.isDebugEnabled())
/* 1445 */       log.debug("  --> Resource not found, returning null");
/* 1446 */     return null;
/*      */   }
/*      */ 
/*      */   public InputStream getResourceAsStream(String name)
/*      */   {
/* 1463 */     if (log.isDebugEnabled())
/* 1464 */       log.debug(new StringBuilder().append("getResourceAsStream(").append(name).append(")").toString());
/* 1465 */     InputStream stream = null;
/*      */ 
/* 1468 */     stream = findLoadedResource(name);
/* 1469 */     if (stream != null) {
/* 1470 */       if (log.isDebugEnabled())
/* 1471 */         log.debug("  --> Returning stream from cache");
/* 1472 */       return stream;
/*      */     }
/*      */ 
/* 1476 */     if (this.delegate) {
/* 1477 */       if (log.isDebugEnabled())
/* 1478 */         log.debug(new StringBuilder().append("  Delegating to parent classloader ").append(this.parent).toString());
/* 1479 */       ClassLoader loader = this.parent;
/* 1480 */       if (loader == null)
/* 1481 */         loader = this.system;
/* 1482 */       stream = loader.getResourceAsStream(name);
/* 1483 */       if (stream != null)
/*      */       {
/* 1485 */         if (log.isDebugEnabled())
/* 1486 */           log.debug("  --> Returning stream from parent");
/* 1487 */         return stream;
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/* 1492 */     if (log.isDebugEnabled())
/* 1493 */       log.debug("  Searching local repositories");
/* 1494 */     URL url = findResource(name);
/* 1495 */     if (url != null)
/*      */     {
/* 1497 */       if (log.isDebugEnabled())
/* 1498 */         log.debug("  --> Returning stream from local");
/* 1499 */       stream = findLoadedResource(name);
/*      */       try {
/* 1501 */         if ((this.hasExternalRepositories) && (stream == null))
/* 1502 */           stream = url.openStream();
/*      */       }
/*      */       catch (IOException e) {
/*      */       }
/* 1506 */       if (stream != null) {
/* 1507 */         return stream;
/*      */       }
/*      */     }
/*      */ 
/* 1511 */     if (!this.delegate) {
/* 1512 */       if (log.isDebugEnabled())
/* 1513 */         log.debug(new StringBuilder().append("  Delegating to parent classloader unconditionally ").append(this.parent).toString());
/* 1514 */       ClassLoader loader = this.parent;
/* 1515 */       if (loader == null)
/* 1516 */         loader = this.system;
/* 1517 */       stream = loader.getResourceAsStream(name);
/* 1518 */       if (stream != null)
/*      */       {
/* 1520 */         if (log.isDebugEnabled())
/* 1521 */           log.debug("  --> Returning stream from parent");
/* 1522 */         return stream;
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/* 1527 */     if (log.isDebugEnabled())
/* 1528 */       log.debug("  --> Resource not found, returning null");
/* 1529 */     return null;
/*      */   }
/*      */ 
/*      */   public Class<?> loadClass(String name)
/*      */     throws ClassNotFoundException
/*      */   {
/* 1546 */     return loadClass(name, false);
/*      */   }
/*      */ 
/*      */   public synchronized Class<?> loadClass(String name, boolean resolve)
/*      */     throws ClassNotFoundException
/*      */   {
/* 1580 */     if (log.isDebugEnabled())
/* 1581 */       log.debug(new StringBuilder().append("loadClass(").append(name).append(", ").append(resolve).append(")").toString());
/* 1582 */     Class clazz = null;
/*      */ 
/* 1585 */     if (!this.started) {
/*      */       try {
/* 1587 */         throw new IllegalStateException();
/*      */       } catch (IllegalStateException e) {
/* 1589 */         log.info(sm.getString("webappClassLoader.stopped", new Object[] { name }), e);
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/* 1594 */     clazz = findLoadedClass0(name);
/* 1595 */     if (clazz != null) {
/* 1596 */       if (log.isDebugEnabled())
/* 1597 */         log.debug("  Returning class from cache");
/* 1598 */       if (resolve)
/* 1599 */         resolveClass(clazz);
/* 1600 */       return clazz;
/*      */     }
/*      */ 
/* 1604 */     clazz = findLoadedClass(name);
/* 1605 */     if (clazz != null) {
/* 1606 */       if (log.isDebugEnabled())
/* 1607 */         log.debug("  Returning class from cache");
/* 1608 */       if (resolve)
/* 1609 */         resolveClass(clazz);
/* 1610 */       return clazz;
/*      */     }
/*      */ 
/*      */     try
/*      */     {
/* 1616 */       clazz = this.system.loadClass(name);
/* 1617 */       if (clazz != null) {
/* 1618 */         if (resolve)
/* 1619 */           resolveClass(clazz);
/* 1620 */         return clazz;
/*      */       }
/*      */     }
/*      */     catch (ClassNotFoundException e)
/*      */     {
/*      */     }
/*      */ 
/* 1627 */     if (this.securityManager != null) {
/* 1628 */       int i = name.lastIndexOf(46);
/* 1629 */       if (i >= 0) {
/*      */         try {
/* 1631 */           this.securityManager.checkPackageAccess(name.substring(0, i));
/*      */         } catch (SecurityException se) {
/* 1633 */           String error = new StringBuilder().append("Security Violation, attempt to use Restricted Class: ").append(name).toString();
/*      */ 
/* 1635 */           log.info(error, se);
/* 1636 */           throw new ClassNotFoundException(error, se);
/*      */         }
/*      */       }
/*      */     }
/*      */ 
/* 1641 */     boolean delegateLoad = (this.delegate) || (filter(name));
/*      */ 
/* 1644 */     if (delegateLoad) {
/* 1645 */       if (log.isDebugEnabled())
/* 1646 */         log.debug(new StringBuilder().append("  Delegating to parent classloader1 ").append(this.parent).toString());
/* 1647 */       ClassLoader loader = this.parent;
/* 1648 */       if (loader == null)
/* 1649 */         loader = this.system;
/*      */       try {
/* 1651 */         clazz = Class.forName(name, false, loader);
/* 1652 */         if (clazz != null) {
/* 1653 */           if (log.isDebugEnabled())
/* 1654 */             log.debug("  Loading class from parent");
/* 1655 */           if (resolve)
/* 1656 */             resolveClass(clazz);
/* 1657 */           return clazz;
/*      */         }
/*      */       }
/*      */       catch (ClassNotFoundException e)
/*      */       {
/*      */       }
/*      */     }
/*      */ 
/* 1665 */     if (log.isDebugEnabled())
/* 1666 */       log.debug("  Searching local repositories");
/*      */     try {
/* 1668 */       clazz = findClass(name);
/* 1669 */       if (clazz != null) {
/* 1670 */         if (log.isDebugEnabled())
/* 1671 */           log.debug("  Loading class from local repository");
/* 1672 */         if (resolve)
/* 1673 */           resolveClass(clazz);
/* 1674 */         return clazz;
/*      */       }
/*      */     }
/*      */     catch (ClassNotFoundException e)
/*      */     {
/*      */     }
/*      */ 
/* 1681 */     if (!delegateLoad) {
/* 1682 */       if (log.isDebugEnabled())
/* 1683 */         log.debug(new StringBuilder().append("  Delegating to parent classloader at end: ").append(this.parent).toString());
/* 1684 */       ClassLoader loader = this.parent;
/* 1685 */       if (loader == null)
/* 1686 */         loader = this.system;
/*      */       try {
/* 1688 */         clazz = Class.forName(name, false, loader);
/* 1689 */         if (clazz != null) {
/* 1690 */           if (log.isDebugEnabled())
/* 1691 */             log.debug("  Loading class from parent");
/* 1692 */           if (resolve)
/* 1693 */             resolveClass(clazz);
/* 1694 */           return clazz;
/*      */         }
/*      */       }
/*      */       catch (ClassNotFoundException e)
/*      */       {
/*      */       }
/*      */     }
/* 1701 */     throw new ClassNotFoundException(name);
/*      */   }
/*      */ 
/*      */   protected PermissionCollection getPermissions(CodeSource codeSource)
/*      */   {
/* 1719 */     String codeUrl = codeSource.getLocation().toString();
/*      */     PermissionCollection pc;
/* 1721 */     if ((pc = (PermissionCollection)this.loaderPC.get(codeUrl)) == null) {
/* 1722 */       pc = super.getPermissions(codeSource);
/* 1723 */       if (pc != null) {
/* 1724 */         Iterator perms = this.permissionList.iterator();
/* 1725 */         while (perms.hasNext()) {
/* 1726 */           Permission p = (Permission)perms.next();
/* 1727 */           pc.add(p);
/*      */         }
/* 1729 */         this.loaderPC.put(codeUrl, pc);
/*      */       }
/*      */     }
/* 1732 */     return pc;
/*      */   }
/*      */ 
/*      */   public URL[] getURLs()
/*      */   {
/* 1746 */     if (this.repositoryURLs != null) {
/* 1747 */       return (URL[])this.repositoryURLs.clone();
/*      */     }
/*      */ 
/* 1750 */     URL[] external = super.getURLs();
/*      */ 
/* 1752 */     int filesLength = this.files.length;
/* 1753 */     int jarFilesLength = this.jarRealFiles.length;
/* 1754 */     int externalsLength = external.length;
/* 1755 */     int off = 0;
/*      */     try
/*      */     {
/* 1760 */       URL[] urls = new URL[filesLength + jarFilesLength + externalsLength];
/* 1761 */       if (this.searchExternalFirst) {
/* 1762 */         for (int i = 0; i < externalsLength; i++) {
/* 1763 */           urls[i] = external[i];
/*      */         }
/* 1765 */         off = externalsLength;
/*      */       }
/* 1767 */       for (int i = 0; i < filesLength; i++) {
/* 1768 */         urls[(off + i)] = getURL(this.files[i], true);
/*      */       }
/* 1770 */       off += filesLength;
/* 1771 */       for (i = 0; i < jarFilesLength; i++) {
/* 1772 */         urls[(off + i)] = getURL(this.jarRealFiles[i], true);
/*      */       }
/* 1774 */       off += jarFilesLength;
/* 1775 */       if (!this.searchExternalFirst) {
/* 1776 */         for (i = 0; i < externalsLength; i++) {
/* 1777 */           urls[(off + i)] = external[i];
/*      */         }
/*      */       }
/*      */ 
/* 1781 */       this.repositoryURLs = urls;
/*      */     }
/*      */     catch (MalformedURLException e) {
/* 1784 */       this.repositoryURLs = new URL[0];
/*      */     }
/*      */ 
/* 1787 */     return (URL[])this.repositoryURLs.clone();
/*      */   }
/*      */ 
/*      */   public void addLifecycleListener(LifecycleListener listener)
/*      */   {
/*      */   }
/*      */ 
/*      */   public LifecycleListener[] findLifecycleListeners()
/*      */   {
/* 1812 */     return new LifecycleListener[0];
/*      */   }
/*      */ 
/*      */   public void removeLifecycleListener(LifecycleListener listener)
/*      */   {
/*      */   }
/*      */ 
/*      */   public LifecycleState getState()
/*      */   {
/* 1834 */     return LifecycleState.NEW;
/*      */   }
/*      */ 
/*      */   public String getStateName()
/*      */   {
/* 1843 */     return getState().toString();
/*      */   }
/*      */ 
/*      */   public void init()
/*      */   {
/*      */   }
/*      */ 
/*      */   public void start()
/*      */     throws LifecycleException
/*      */   {
/* 1861 */     this.started = true;
/* 1862 */     String encoding = null;
/*      */     try {
/* 1864 */       encoding = System.getProperty("file.encoding");
/*      */     } catch (SecurityException e) {
/* 1866 */       return;
/*      */     }
/* 1868 */     if (encoding.indexOf("EBCDIC") != -1)
/* 1869 */       this.needConvert = true;
/*      */   }
/*      */ 
/*      */   public boolean isStarted()
/*      */   {
/* 1876 */     return this.started;
/*      */   }
/*      */ 
/*      */   public void stop()
/*      */     throws LifecycleException
/*      */   {
/* 1889 */     clearReferences();
/*      */ 
/* 1891 */     this.started = false;
/*      */ 
/* 1893 */     int length = this.files.length;
/* 1894 */     for (int i = 0; i < length; i++) {
/* 1895 */       this.files[i] = null;
/*      */     }
/*      */ 
/* 1898 */     length = this.jarFiles.length;
/* 1899 */     for (int i = 0; i < length; i++) {
/*      */       try {
/* 1901 */         if (this.jarFiles[i] != null)
/* 1902 */           this.jarFiles[i].close();
/*      */       }
/*      */       catch (IOException e)
/*      */       {
/*      */       }
/* 1907 */       this.jarFiles[i] = null;
/*      */     }
/*      */ 
/* 1910 */     this.notFoundResources.clear();
/* 1911 */     this.resourceEntries.clear();
/* 1912 */     this.resources = null;
/* 1913 */     this.repositories = null;
/* 1914 */     this.repositoryURLs = null;
/* 1915 */     this.files = null;
/* 1916 */     this.jarFiles = null;
/* 1917 */     this.jarRealFiles = null;
/* 1918 */     this.jarPath = null;
/* 1919 */     this.jarNames = null;
/* 1920 */     this.lastModifiedDates = null;
/* 1921 */     this.paths = null;
/* 1922 */     this.hasExternalRepositories = false;
/* 1923 */     this.parent = null;
/*      */ 
/* 1925 */     this.permissionList.clear();
/* 1926 */     this.loaderPC.clear();
/*      */ 
/* 1928 */     if (this.loaderDir != null)
/* 1929 */       deleteDir(this.loaderDir);
/*      */   }
/*      */ 
/*      */   public void destroy()
/*      */   {
/*      */   }
/*      */ 
/*      */   public void closeJARs(boolean force)
/*      */   {
/* 1946 */     if (this.jarFiles.length > 0)
/* 1947 */       synchronized (this.jarFiles) {
/* 1948 */         if ((force) || (System.currentTimeMillis() > this.lastJarAccessed + 90000L))
/*      */         {
/* 1950 */           for (int i = 0; i < this.jarFiles.length; i++)
/*      */             try {
/* 1952 */               if (this.jarFiles[i] != null) {
/* 1953 */                 this.jarFiles[i].close();
/* 1954 */                 this.jarFiles[i] = null;
/*      */               }
/*      */             } catch (IOException e) {
/* 1957 */               if (log.isDebugEnabled())
/* 1958 */                 log.debug("Failed to close JAR", e);
/*      */             }
/*      */         }
/*      */       }
/*      */   }
/*      */ 
/*      */   protected void clearReferences()
/*      */   {
/* 1977 */     clearReferencesJdbc();
/*      */ 
/* 1980 */     clearReferencesThreads();
/*      */ 
/* 1983 */     checkThreadLocalsForLeaks();
/*      */ 
/* 1986 */     clearReferencesRmiTargets();
/*      */ 
/* 1990 */     if (this.clearReferencesStatic) {
/* 1991 */       clearReferencesStaticFinal();
/*      */     }
/*      */ 
/* 1995 */     IntrospectionUtils.clear();
/*      */ 
/* 1998 */     if (this.clearReferencesLogFactoryRelease) {
/* 1999 */       LogFactory.release(this);
/*      */     }
/*      */ 
/* 2007 */     clearReferencesResourceBundles();
/*      */ 
/* 2010 */     Introspector.flushCaches();
/*      */   }
/*      */ 
/*      */   private final void clearReferencesJdbc()
/*      */   {
/* 2034 */     InputStream is = getResourceAsStream("org/apache/catalina/loader/JdbcLeakPrevention.class");
/*      */ 
/* 2038 */     byte[] classBytes = new byte[2048];
/* 2039 */     int offset = 0;
/*      */     try {
/* 2041 */       int read = is.read(classBytes, offset, classBytes.length - offset);
/* 2042 */       while (read > -1) {
/* 2043 */         offset += read;
/* 2044 */         if (offset == classBytes.length)
/*      */         {
/* 2046 */           byte[] tmp = new byte[classBytes.length * 2];
/* 2047 */           System.arraycopy(classBytes, 0, tmp, 0, classBytes.length);
/* 2048 */           classBytes = tmp;
/*      */         }
/* 2050 */         read = is.read(classBytes, offset, classBytes.length - offset);
/*      */       }
/* 2052 */       Class lpClass = defineClass("org.apache.catalina.loader.JdbcLeakPrevention", classBytes, 0, offset, getClass().getProtectionDomain());
/*      */ 
/* 2055 */       Object obj = lpClass.newInstance();
/*      */ 
/* 2057 */       List driverNames = (List)obj.getClass().getMethod("clearJdbcDriverRegistrations", new Class[0]).invoke(obj, new Object[0]);
/*      */ 
/* 2059 */       for (String name : driverNames) {
/* 2060 */         log.error(sm.getString("webappClassLoader.clearJdbc", new Object[] { this.contextName, name }));
/*      */       }
/*      */     }
/*      */     catch (Exception ioe)
/*      */     {
/* 2065 */       Throwable t = ExceptionUtils.unwrapInvocationTargetException(e);
/* 2066 */       ExceptionUtils.handleThrowable(t);
/* 2067 */       log.warn(sm.getString("webappClassLoader.jdbcRemoveFailed", new Object[] { this.contextName }), t);
/*      */     }
/*      */     finally {
/* 2070 */       if (is != null)
/*      */         try {
/* 2072 */           is.close();
/*      */         } catch (IOException ioe) {
/* 2074 */           log.warn(sm.getString("webappClassLoader.jdbcRemoveStreamError", new Object[] { this.contextName }), ioe);
/*      */         }
/*      */     }
/*      */   }
/*      */ 
/*      */   private final void clearReferencesStaticFinal()
/*      */   {
/* 2086 */     Collection values = ((HashMap)this.resourceEntries.clone()).values();
/*      */ 
/* 2088 */     Iterator loadedClasses = values.iterator();
/*      */ 
/* 2093 */     while (loadedClasses.hasNext()) {
/* 2094 */       ResourceEntry entry = (ResourceEntry)loadedClasses.next();
/* 2095 */       if (entry.loadedClass != null) {
/* 2096 */         Class clazz = entry.loadedClass;
/*      */         try {
/* 2098 */           Field[] fields = clazz.getDeclaredFields();
/* 2099 */           for (int i = 0; i < fields.length; i++)
/* 2100 */             if (Modifier.isStatic(fields[i].getModifiers())) {
/* 2101 */               fields[i].get(null);
/* 2102 */               break;
/*      */             }
/*      */         }
/*      */         catch (Throwable t)
/*      */         {
/*      */         }
/*      */       }
/*      */     }
/* 2110 */     loadedClasses = values.iterator();
/* 2111 */     while (loadedClasses.hasNext()) {
/* 2112 */       ResourceEntry entry = (ResourceEntry)loadedClasses.next();
/* 2113 */       if (entry.loadedClass != null) {
/* 2114 */         Class clazz = entry.loadedClass;
/*      */         try {
/* 2116 */           Field[] fields = clazz.getDeclaredFields();
/* 2117 */           for (int i = 0; i < fields.length; i++) {
/* 2118 */             Field field = fields[i];
/* 2119 */             int mods = field.getModifiers();
/* 2120 */             if ((field.getType().isPrimitive()) || (field.getName().indexOf("$") != -1))
/*      */             {
/*      */               continue;
/*      */             }
/* 2124 */             if (!Modifier.isStatic(mods)) continue;
/*      */             try {
/* 2126 */               field.setAccessible(true);
/* 2127 */               if (Modifier.isFinal(mods)) {
/* 2128 */                 if ((!field.getType().getName().startsWith("java.")) && (!field.getType().getName().startsWith("javax.")))
/*      */                 {
/* 2130 */                   nullInstance(field.get(null));
/*      */                 }
/*      */               } else {
/* 2133 */                 field.set(null, null);
/* 2134 */                 if (log.isDebugEnabled())
/* 2135 */                   log.debug(new StringBuilder().append("Set field ").append(field.getName()).append(" to null in class ").append(clazz.getName()).toString());
/*      */               }
/*      */             }
/*      */             catch (Throwable t)
/*      */             {
/* 2140 */               ExceptionUtils.handleThrowable(t);
/* 2141 */               if (log.isDebugEnabled()) {
/* 2142 */                 log.debug(new StringBuilder().append("Could not set field ").append(field.getName()).append(" to null in class ").append(clazz.getName()).toString(), t);
/*      */               }
/*      */             }
/*      */           }
/*      */         }
/*      */         catch (Throwable t)
/*      */         {
/* 2149 */           ExceptionUtils.handleThrowable(t);
/* 2150 */           if (log.isDebugEnabled())
/* 2151 */             log.debug(new StringBuilder().append("Could not clean fields for class ").append(clazz.getName()).toString(), t);
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   private void nullInstance(Object instance)
/*      */   {
/* 2161 */     if (instance == null) {
/* 2162 */       return;
/*      */     }
/* 2164 */     Field[] fields = instance.getClass().getDeclaredFields();
/* 2165 */     for (int i = 0; i < fields.length; i++) {
/* 2166 */       Field field = fields[i];
/* 2167 */       int mods = field.getModifiers();
/* 2168 */       if ((field.getType().isPrimitive()) || (field.getName().indexOf("$") != -1)) {
/*      */         continue;
/*      */       }
/*      */       try
/*      */       {
/* 2173 */         field.setAccessible(true);
/* 2174 */         if ((Modifier.isStatic(mods)) && (Modifier.isFinal(mods)))
/*      */         {
/*      */           continue;
/*      */         }
/* 2178 */         Object value = field.get(instance);
/* 2179 */         if (null != value) {
/* 2180 */           Class valueClass = value.getClass();
/* 2181 */           if (!loadedByThisOrChild(valueClass)) {
/* 2182 */             if (log.isDebugEnabled()) {
/* 2183 */               log.debug(new StringBuilder().append("Not setting field ").append(field.getName()).append(" to null in object of class ").append(instance.getClass().getName()).append(" because the referenced object was of type ").append(valueClass.getName()).append(" which was not loaded by this WebappClassLoader.").toString());
/*      */             }
/*      */ 
/*      */           }
/*      */           else
/*      */           {
/* 2191 */             field.set(instance, null);
/* 2192 */             if (log.isDebugEnabled())
/* 2193 */               log.debug(new StringBuilder().append("Set field ").append(field.getName()).append(" to null in class ").append(instance.getClass().getName()).toString());
/*      */           }
/*      */         }
/*      */       }
/*      */       catch (Throwable t)
/*      */       {
/* 2199 */         ExceptionUtils.handleThrowable(t);
/* 2200 */         if (log.isDebugEnabled())
/* 2201 */           log.debug(new StringBuilder().append("Could not set field ").append(field.getName()).append(" to null in object instance of class ").append(instance.getClass().getName()).toString(), t);
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   private void clearReferencesThreads()
/*      */   {
/* 2212 */     Thread[] threads = getThreads();
/*      */ 
/* 2215 */     for (Thread thread : threads)
/* 2216 */       if (thread != null) {
/* 2217 */         ClassLoader ccl = thread.getContextClassLoader();
/* 2218 */         if (ccl != this)
/*      */           continue;
/* 2220 */         if (thread == Thread.currentThread())
/*      */         {
/*      */           continue;
/*      */         }
/*      */ 
/* 2225 */         ThreadGroup tg = thread.getThreadGroup();
/* 2226 */         if ((tg != null) && (JVM_THREAD_GROUP_NAMES.contains(tg.getName())))
/*      */         {
/* 2230 */           if ((!this.clearReferencesHttpClientKeepAliveThread) || (!thread.getName().equals("Keep-Alive-Timer")))
/*      */             continue;
/* 2232 */           thread.setContextClassLoader(this.parent);
/* 2233 */           log.debug(sm.getString("webappClassLoader.checkThreadsHttpClient"));
/*      */         }
/*      */         else
/*      */         {
/* 2242 */           if (!thread.isAlive())
/*      */           {
/*      */             continue;
/*      */           }
/*      */ 
/* 2247 */           if ((thread.getClass().getName().equals("java.util.TimerThread")) && (this.clearReferencesStopTimerThreads))
/*      */           {
/* 2250 */             clearReferencesStopTimerThread(thread);
/*      */           }
/*      */           else
/*      */           {
/* 2254 */             if (isRequestThread(thread)) {
/* 2255 */               log.error(sm.getString("webappClassLoader.warnRequestThread", new Object[] { this.contextName, thread.getName() }));
/*      */             }
/*      */             else {
/* 2258 */               log.error(sm.getString("webappClassLoader.warnThread", new Object[] { this.contextName, thread.getName() }));
/*      */             }
/*      */ 
/* 2264 */             if (!this.clearReferencesStopThreads)
/*      */             {
/*      */               continue;
/*      */             }
/*      */ 
/*      */             try
/*      */             {
/* 2271 */               Field targetField = thread.getClass().getDeclaredField("target");
/*      */ 
/* 2273 */               targetField.setAccessible(true);
/* 2274 */               Object target = targetField.get(thread);
/*      */ 
/* 2276 */               if ((target != null) && (target.getClass().getCanonicalName().equals("java.util.concurrent.ThreadPoolExecutor.Worker")))
/*      */               {
/* 2279 */                 Field executorField = target.getClass().getDeclaredField("this$0");
/*      */ 
/* 2281 */                 executorField.setAccessible(true);
/* 2282 */                 Object executor = executorField.get(target);
/* 2283 */                 if ((executor instanceof ThreadPoolExecutor))
/* 2284 */                   ((ThreadPoolExecutor)executor).shutdownNow();
/*      */               }
/*      */             }
/*      */             catch (SecurityException e) {
/* 2288 */               log.warn(sm.getString("webappClassLoader.stopThreadFail", new Object[] { thread.getName(), this.contextName }), e);
/*      */             }
/*      */             catch (NoSuchFieldException e)
/*      */             {
/* 2292 */               log.warn(sm.getString("webappClassLoader.stopThreadFail", new Object[] { thread.getName(), this.contextName }), e);
/*      */             }
/*      */             catch (IllegalArgumentException e)
/*      */             {
/* 2296 */               log.warn(sm.getString("webappClassLoader.stopThreadFail", new Object[] { thread.getName(), this.contextName }), e);
/*      */             }
/*      */             catch (IllegalAccessException e)
/*      */             {
/* 2300 */               log.warn(sm.getString("webappClassLoader.stopThreadFail", new Object[] { thread.getName(), this.contextName }), e);
/*      */             }
/*      */ 
/* 2309 */             thread.stop();
/*      */           }
/*      */         }
/*      */       }
/*      */   }
/*      */ 
/*      */   private boolean isRequestThread(Thread thread)
/*      */   {
/* 2322 */     StackTraceElement[] elements = thread.getStackTrace();
/*      */ 
/* 2324 */     if ((elements == null) || (elements.length == 0))
/*      */     {
/* 2327 */       return false;
/*      */     }
/*      */ 
/* 2334 */     for (int i = 0; i < elements.length; i++) {
/* 2335 */       StackTraceElement element = elements[(elements.length - (i + 1))];
/* 2336 */       if ("org.apache.catalina.connector.CoyoteAdapter".equals(element.getClassName()))
/*      */       {
/* 2338 */         return true;
/*      */       }
/*      */     }
/* 2341 */     return false;
/*      */   }
/*      */ 
/*      */   private void clearReferencesStopTimerThread(Thread thread)
/*      */   {
/*      */     try
/*      */     {
/* 2353 */       Field newTasksMayBeScheduledField = thread.getClass().getDeclaredField("newTasksMayBeScheduled");
/*      */ 
/* 2355 */       newTasksMayBeScheduledField.setAccessible(true);
/* 2356 */       Field queueField = thread.getClass().getDeclaredField("queue");
/* 2357 */       queueField.setAccessible(true);
/*      */ 
/* 2359 */       Object queue = queueField.get(thread);
/*      */ 
/* 2361 */       Method clearMethod = queue.getClass().getDeclaredMethod("clear", new Class[0]);
/* 2362 */       clearMethod.setAccessible(true);
/*      */ 
/* 2364 */       synchronized (queue) {
/* 2365 */         newTasksMayBeScheduledField.setBoolean(thread, false);
/* 2366 */         clearMethod.invoke(queue, new Object[0]);
/* 2367 */         queue.notify();
/*      */       }
/*      */ 
/* 2370 */       log.error(sm.getString("webappClassLoader.warnTimerThread", new Object[] { this.contextName, thread.getName() }));
/*      */     }
/*      */     catch (Exception e)
/*      */     {
/* 2375 */       Throwable t = ExceptionUtils.unwrapInvocationTargetException(e);
/* 2376 */       ExceptionUtils.handleThrowable(t);
/* 2377 */       log.warn(sm.getString("webappClassLoader.stopTimerThreadFail", new Object[] { thread.getName(), this.contextName }), t);
/*      */     }
/*      */   }
/*      */ 
/*      */   private void checkThreadLocalsForLeaks()
/*      */   {
/* 2384 */     Thread[] threads = getThreads();
/*      */     try
/*      */     {
/* 2389 */       Field threadLocalsField = Thread.class.getDeclaredField("threadLocals");
/*      */ 
/* 2391 */       threadLocalsField.setAccessible(true);
/* 2392 */       Field inheritableThreadLocalsField = Thread.class.getDeclaredField("inheritableThreadLocals");
/*      */ 
/* 2394 */       inheritableThreadLocalsField.setAccessible(true);
/*      */ 
/* 2397 */       Class tlmClass = Class.forName("java.lang.ThreadLocal$ThreadLocalMap");
/*      */ 
/* 2399 */       Field tableField = tlmClass.getDeclaredField("table");
/* 2400 */       tableField.setAccessible(true);
/*      */ 
/* 2402 */       for (int i = 0; i < threads.length; i++)
/*      */       {
/* 2404 */         if (threads[i] == null)
/*      */           continue;
/* 2406 */         Object threadLocalMap = threadLocalsField.get(threads[i]);
/* 2407 */         checkThreadLocalMapForLeaks(threadLocalMap, tableField);
/*      */ 
/* 2409 */         threadLocalMap = inheritableThreadLocalsField.get(threads[i]);
/*      */ 
/* 2411 */         checkThreadLocalMapForLeaks(threadLocalMap, tableField);
/*      */       }
/*      */     }
/*      */     catch (SecurityException e) {
/* 2415 */       log.warn(sm.getString("webappClassLoader.checkThreadLocalsForLeaksFail", new Object[] { this.contextName }), e);
/*      */     }
/*      */     catch (NoSuchFieldException e) {
/* 2418 */       log.warn(sm.getString("webappClassLoader.checkThreadLocalsForLeaksFail", new Object[] { this.contextName }), e);
/*      */     }
/*      */     catch (ClassNotFoundException e) {
/* 2421 */       log.warn(sm.getString("webappClassLoader.checkThreadLocalsForLeaksFail", new Object[] { this.contextName }), e);
/*      */     }
/*      */     catch (IllegalArgumentException e) {
/* 2424 */       log.warn(sm.getString("webappClassLoader.checkThreadLocalsForLeaksFail", new Object[] { this.contextName }), e);
/*      */     }
/*      */     catch (IllegalAccessException e) {
/* 2427 */       log.warn(sm.getString("webappClassLoader.checkThreadLocalsForLeaksFail", new Object[] { this.contextName }), e);
/*      */     }
/*      */   }
/*      */ 
/*      */   private void checkThreadLocalMapForLeaks(Object map, Field internalTableField)
/*      */     throws IllegalAccessException, NoSuchFieldException
/*      */   {
/* 2441 */     if (map != null) {
/* 2442 */       Object[] table = (Object[])(Object[])internalTableField.get(map);
/* 2443 */       if (table != null)
/* 2444 */         for (int j = 0; j < table.length; j++)
/* 2445 */           if (table[j] != null) {
/* 2446 */             boolean potentialLeak = false;
/*      */ 
/* 2448 */             Object key = ((Reference)table[j]).get();
/* 2449 */             if ((equals(key)) || (loadedByThisOrChild(key))) {
/* 2450 */               potentialLeak = true;
/*      */             }
/*      */ 
/* 2453 */             Field valueField = table[j].getClass().getDeclaredField("value");
/*      */ 
/* 2455 */             valueField.setAccessible(true);
/* 2456 */             Object value = valueField.get(table[j]);
/* 2457 */             if ((equals(value)) || (loadedByThisOrChild(value))) {
/* 2458 */               potentialLeak = true;
/*      */             }
/* 2460 */             if (potentialLeak) {
/* 2461 */               Object[] args = new Object[5];
/* 2462 */               args[0] = this.contextName;
/* 2463 */               if (key != null) {
/* 2464 */                 args[1] = getPrettyClassName(key.getClass());
/*      */                 try {
/* 2466 */                   args[2] = key.toString();
/*      */                 } catch (Exception e) {
/* 2468 */                   log.error(sm.getString("webappClassLoader.checkThreadLocalsForLeaks.badKey", new Object[] { args[1] }), e);
/*      */ 
/* 2471 */                   args[2] = sm.getString("webappClassLoader.checkThreadLocalsForLeaks.unknown");
/*      */                 }
/*      */               }
/*      */ 
/* 2475 */               if (value != null) {
/* 2476 */                 args[3] = getPrettyClassName(value.getClass());
/*      */                 try {
/* 2478 */                   args[4] = value.toString();
/*      */                 } catch (Exception e) {
/* 2480 */                   log.error(sm.getString("webappClassLoader.checkThreadLocalsForLeaks.badValue", new Object[] { args[3] }), e);
/*      */ 
/* 2483 */                   args[4] = sm.getString("webappClassLoader.checkThreadLocalsForLeaks.unknown");
/*      */                 }
/*      */               }
/*      */ 
/* 2487 */               if (value == null) {
/* 2488 */                 if (log.isDebugEnabled()) {
/* 2489 */                   log.debug(sm.getString("webappClassLoader.checkThreadLocalsForLeaksDebug", args));
/*      */                 }
/*      */               }
/*      */               else
/*      */               {
/* 2494 */                 log.error(sm.getString("webappClassLoader.checkThreadLocalsForLeaks", args));
/*      */               }
/*      */             }
/*      */           }
/*      */     }
/*      */   }
/*      */ 
/*      */   private String getPrettyClassName(Class<?> clazz)
/*      */   {
/* 2506 */     String name = clazz.getCanonicalName();
/* 2507 */     if (name == null) {
/* 2508 */       name = clazz.getName();
/*      */     }
/* 2510 */     return name;
/*      */   }
/*      */ 
/*      */   private boolean loadedByThisOrChild(Object o)
/*      */   {
/* 2519 */     if (o == null)
/* 2520 */       return false;
/*      */     Class clazz;
/*      */     Class clazz;
/* 2524 */     if ((o instanceof Class))
/* 2525 */       clazz = (Class)o;
/*      */     else {
/* 2527 */       clazz = o.getClass();
/*      */     }
/*      */ 
/* 2530 */     ClassLoader cl = clazz.getClassLoader();
/* 2531 */     while (cl != null) {
/* 2532 */       if (cl == this) {
/* 2533 */         return true;
/*      */       }
/* 2535 */       cl = cl.getParent();
/*      */     }
/* 2537 */     return false;
/*      */   }
/*      */ 
/*      */   private Thread[] getThreads()
/*      */   {
/* 2545 */     ThreadGroup tg = Thread.currentThread().getThreadGroup();
/*      */ 
/* 2547 */     while (tg.getParent() != null) {
/* 2548 */       tg = tg.getParent();
/*      */     }
/*      */ 
/* 2551 */     int threadCountGuess = tg.activeCount() + 50;
/* 2552 */     Thread[] threads = new Thread[threadCountGuess];
/* 2553 */     int threadCountActual = tg.enumerate(threads);
/*      */ 
/* 2555 */     while (threadCountActual == threadCountGuess) {
/* 2556 */       threadCountGuess *= 2;
/* 2557 */       threads = new Thread[threadCountGuess];
/*      */ 
/* 2560 */       threadCountActual = tg.enumerate(threads);
/*      */     }
/*      */ 
/* 2563 */     return threads;
/*      */   }
/*      */ 
/*      */   private void clearReferencesRmiTargets()
/*      */   {
/*      */     try
/*      */     {
/* 2574 */       Class objectTargetClass = Class.forName("sun.rmi.transport.Target");
/*      */ 
/* 2576 */       Field cclField = objectTargetClass.getDeclaredField("ccl");
/* 2577 */       cclField.setAccessible(true);
/*      */ 
/* 2580 */       Class objectTableClass = Class.forName("sun.rmi.transport.ObjectTable");
/*      */ 
/* 2582 */       Field objTableField = objectTableClass.getDeclaredField("objTable");
/* 2583 */       objTableField.setAccessible(true);
/* 2584 */       Object objTable = objTableField.get(null);
/* 2585 */       if (objTable == null) {
/* 2586 */         return;
/*      */       }
/*      */ 
/* 2590 */       if ((objTable instanceof Map)) {
/* 2591 */         Iterator iter = ((Map)objTable).values().iterator();
/* 2592 */         while (iter.hasNext()) {
/* 2593 */           Object obj = iter.next();
/* 2594 */           Object cclObject = cclField.get(obj);
/* 2595 */           if (this == cclObject) {
/* 2596 */             iter.remove();
/*      */           }
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/* 2602 */       Field implTableField = objectTableClass.getDeclaredField("implTable");
/* 2603 */       implTableField.setAccessible(true);
/* 2604 */       Object implTable = implTableField.get(null);
/* 2605 */       if (implTable == null) {
/* 2606 */         return;
/*      */       }
/*      */ 
/* 2610 */       if ((implTable instanceof Map)) {
/* 2611 */         Iterator iter = ((Map)implTable).values().iterator();
/* 2612 */         while (iter.hasNext()) {
/* 2613 */           Object obj = iter.next();
/* 2614 */           Object cclObject = cclField.get(obj);
/* 2615 */           if (this == cclObject)
/* 2616 */             iter.remove();
/*      */         }
/*      */       }
/*      */     }
/*      */     catch (ClassNotFoundException e) {
/* 2621 */       log.info(sm.getString("webappClassLoader.clearRmiInfo", new Object[] { this.contextName }), e);
/*      */     }
/*      */     catch (SecurityException e) {
/* 2624 */       log.warn(sm.getString("webappClassLoader.clearRmiFail", new Object[] { this.contextName }), e);
/*      */     }
/*      */     catch (NoSuchFieldException e) {
/* 2627 */       log.warn(sm.getString("webappClassLoader.clearRmiFail", new Object[] { this.contextName }), e);
/*      */     }
/*      */     catch (IllegalArgumentException e) {
/* 2630 */       log.warn(sm.getString("webappClassLoader.clearRmiFail", new Object[] { this.contextName }), e);
/*      */     }
/*      */     catch (IllegalAccessException e) {
/* 2633 */       log.warn(sm.getString("webappClassLoader.clearRmiFail", new Object[] { this.contextName }), e);
/*      */     }
/*      */   }
/*      */ 
/*      */   private void clearReferencesResourceBundles()
/*      */   {
/*      */     try
/*      */     {
/* 2654 */       Field cacheListField = ResourceBundle.class.getDeclaredField("cacheList");
/*      */ 
/* 2656 */       cacheListField.setAccessible(true);
/*      */ 
/* 2661 */       Map cacheList = (Map)cacheListField.get(null);
/*      */ 
/* 2664 */       Set keys = cacheList.keySet();
/*      */ 
/* 2666 */       Field loaderRefField = null;
/*      */ 
/* 2669 */       Iterator keysIter = keys.iterator();
/*      */ 
/* 2671 */       int countRemoved = 0;
/*      */ 
/* 2673 */       while (keysIter.hasNext()) {
/* 2674 */         Object key = keysIter.next();
/*      */ 
/* 2676 */         if (loaderRefField == null) {
/* 2677 */           loaderRefField = key.getClass().getDeclaredField("loaderRef");
/*      */ 
/* 2679 */           loaderRefField.setAccessible(true);
/*      */         }
/* 2681 */         WeakReference loaderRef = (WeakReference)loaderRefField.get(key);
/*      */ 
/* 2684 */         ClassLoader loader = (ClassLoader)loaderRef.get();
/*      */ 
/* 2686 */         while ((loader != null) && (loader != this)) {
/* 2687 */           loader = loader.getParent();
/*      */         }
/*      */ 
/* 2690 */         if (loader != null) {
/* 2691 */           keysIter.remove();
/* 2692 */           countRemoved++;
/*      */         }
/*      */       }
/*      */ 
/* 2696 */       if ((countRemoved > 0) && (log.isDebugEnabled())) {
/* 2697 */         log.debug(sm.getString("webappClassLoader.clearReferencesResourceBundlesCount", new Object[] { Integer.valueOf(countRemoved), this.contextName }));
/*      */       }
/*      */     }
/*      */     catch (SecurityException e)
/*      */     {
/* 2702 */       log.error(sm.getString("webappClassLoader.clearReferencesResourceBundlesFail", new Object[] { this.contextName }), e);
/*      */     }
/*      */     catch (NoSuchFieldException e)
/*      */     {
/* 2706 */       if (System.getProperty("java.vendor").startsWith("Sun")) {
/* 2707 */         log.error(sm.getString("webappClassLoader.clearReferencesResourceBundlesFail", new Object[] { this.contextName }), e);
/*      */       }
/*      */       else
/*      */       {
/* 2711 */         log.debug(sm.getString("webappClassLoader.clearReferencesResourceBundlesFail", new Object[] { this.contextName }), e);
/*      */       }
/*      */     }
/*      */     catch (IllegalArgumentException e)
/*      */     {
/* 2716 */       log.error(sm.getString("webappClassLoader.clearReferencesResourceBundlesFail", new Object[] { this.contextName }), e);
/*      */     }
/*      */     catch (IllegalAccessException e)
/*      */     {
/* 2720 */       log.error(sm.getString("webappClassLoader.clearReferencesResourceBundlesFail", new Object[] { this.contextName }), e);
/*      */     }
/*      */   }
/*      */ 
/*      */   protected boolean openJARs()
/*      */   {
/* 2731 */     if ((this.started) && (this.jarFiles.length > 0)) {
/* 2732 */       this.lastJarAccessed = System.currentTimeMillis();
/* 2733 */       if (this.jarFiles[0] == null) {
/* 2734 */         for (int i = 0; i < this.jarFiles.length; i++) {
/*      */           try {
/* 2736 */             this.jarFiles[i] = new JarFile(this.jarRealFiles[i]);
/*      */           } catch (IOException e) {
/* 2738 */             if (log.isDebugEnabled()) {
/* 2739 */               log.debug("Failed to open JAR", e);
/*      */             }
/* 2741 */             return false;
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/* 2746 */     return true;
/*      */   }
/*      */ 
/*      */   protected Class<?> findClassInternal(String name)
/*      */     throws ClassNotFoundException
/*      */   {
/* 2758 */     if (!validate(name)) {
/* 2759 */       throw new ClassNotFoundException(name);
/*      */     }
/* 2761 */     String tempPath = name.replace('.', '/');
/* 2762 */     String classPath = new StringBuilder().append(tempPath).append(".class").toString();
/*      */ 
/* 2764 */     ResourceEntry entry = null;
/*      */ 
/* 2766 */     if (this.securityManager != null) {
/* 2767 */       PrivilegedAction dp = new PrivilegedFindResourceByName(name, classPath);
/*      */ 
/* 2769 */       entry = (ResourceEntry)AccessController.doPrivileged(dp);
/*      */     } else {
/* 2771 */       entry = findResourceInternal(name, classPath);
/*      */     }
/*      */ 
/* 2774 */     if (entry == null) {
/* 2775 */       throw new ClassNotFoundException(name);
/*      */     }
/* 2777 */     Class clazz = entry.loadedClass;
/* 2778 */     if (clazz != null) {
/* 2779 */       return clazz;
/*      */     }
/* 2781 */     synchronized (this) {
/* 2782 */       clazz = entry.loadedClass;
/* 2783 */       if (clazz != null) {
/* 2784 */         return clazz;
/*      */       }
/* 2786 */       if (entry.binaryContent == null) {
/* 2787 */         throw new ClassNotFoundException(name);
/*      */       }
/*      */ 
/* 2790 */       String packageName = null;
/* 2791 */       int pos = name.lastIndexOf(46);
/* 2792 */       if (pos != -1) {
/* 2793 */         packageName = name.substring(0, pos);
/*      */       }
/* 2795 */       Package pkg = null;
/*      */ 
/* 2797 */       if (packageName != null) {
/* 2798 */         pkg = getPackage(packageName);
/*      */ 
/* 2800 */         if (pkg == null) {
/*      */           try {
/* 2802 */             if (entry.manifest == null) {
/* 2803 */               definePackage(packageName, null, null, null, null, null, null, null);
/*      */             }
/*      */             else {
/* 2806 */               definePackage(packageName, entry.manifest, entry.codeBase);
/*      */             }
/*      */           }
/*      */           catch (IllegalArgumentException e)
/*      */           {
/*      */           }
/* 2812 */           pkg = getPackage(packageName);
/*      */         }
/*      */       }
/*      */ 
/* 2816 */       if (this.securityManager != null)
/*      */       {
/* 2819 */         if (pkg != null) {
/* 2820 */           boolean sealCheck = true;
/* 2821 */           if (pkg.isSealed())
/* 2822 */             sealCheck = pkg.isSealed(entry.codeBase);
/*      */           else {
/* 2824 */             sealCheck = (entry.manifest == null) || (!isPackageSealed(packageName, entry.manifest));
/*      */           }
/*      */ 
/* 2827 */           if (!sealCheck) {
/* 2828 */             throw new SecurityException(new StringBuilder().append("Sealing violation loading ").append(name).append(" : Package ").append(packageName).append(" is sealed.").toString());
/*      */           }
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/*      */       try
/*      */       {
/* 2836 */         clazz = defineClass(name, entry.binaryContent, 0, entry.binaryContent.length, new CodeSource(entry.codeBase, entry.certificates));
/*      */       }
/*      */       catch (UnsupportedClassVersionError ucve)
/*      */       {
/* 2840 */         throw new UnsupportedClassVersionError(new StringBuilder().append(ucve.getLocalizedMessage()).append(" ").append(sm.getString("webappClassLoader.wrongVersion", new Object[] { name })).toString());
/*      */       }
/*      */ 
/* 2845 */       entry.loadedClass = clazz;
/* 2846 */       entry.binaryContent = null;
/* 2847 */       entry.source = null;
/* 2848 */       entry.codeBase = null;
/* 2849 */       entry.manifest = null;
/* 2850 */       entry.certificates = null;
/*      */     }
/*      */ 
/* 2853 */     return clazz;
/*      */   }
/*      */ 
/*      */   protected ResourceEntry findResourceInternal(File file, String path)
/*      */   {
/* 2863 */     ResourceEntry entry = new ResourceEntry();
/*      */     try {
/* 2865 */       entry.source = getURI(new File(file, path));
/* 2866 */       entry.codeBase = getURL(new File(file, path), false);
/*      */     } catch (MalformedURLException e) {
/* 2868 */       return null;
/*      */     }
/* 2870 */     return entry;
/*      */   }
/*      */ 
/*      */   protected ResourceEntry findResourceInternal(String name, String path)
/*      */   {
/* 2881 */     if (!this.started) {
/* 2882 */       log.info(sm.getString("webappClassLoader.stopped", new Object[] { name }));
/* 2883 */       return null;
/*      */     }
/*      */ 
/* 2886 */     if ((name == null) || (path == null)) {
/* 2887 */       return null;
/*      */     }
/* 2889 */     ResourceEntry entry = (ResourceEntry)this.resourceEntries.get(name);
/* 2890 */     if (entry != null) {
/* 2891 */       return entry;
/*      */     }
/* 2893 */     int contentLength = -1;
/* 2894 */     InputStream binaryStream = null;
/*      */ 
/* 2896 */     int jarFilesLength = this.jarFiles.length;
/* 2897 */     int repositoriesLength = this.repositories.length;
/*      */ 
/* 2901 */     Resource resource = null;
/*      */ 
/* 2903 */     boolean fileNeedConvert = false;
/*      */     ResourceAttributes attributes;
/* 2905 */     for (int i = 0; (entry == null) && (i < repositoriesLength); i++) {
/*      */       try
/*      */       {
/* 2908 */         String fullPath = new StringBuilder().append(this.repositories[i]).append(path).toString();
/*      */ 
/* 2910 */         Object lookupResult = this.resources.lookup(fullPath);
/* 2911 */         if ((lookupResult instanceof Resource)) {
/* 2912 */           resource = (Resource)lookupResult;
/*      */         }
/*      */ 
/* 2918 */         attributes = (ResourceAttributes)this.resources.getAttributes(fullPath);
/*      */ 
/* 2920 */         contentLength = (int)attributes.getContentLength();
/* 2921 */         String canonicalPath = attributes.getCanonicalPath();
/* 2922 */         if (canonicalPath != null)
/*      */         {
/* 2927 */           entry = findResourceInternal(new File(canonicalPath), "");
/*      */         }
/*      */         else
/*      */         {
/* 2931 */           entry = findResourceInternal(this.files[i], path);
/*      */         }
/* 2933 */         entry.lastModified = attributes.getLastModified();
/*      */ 
/* 2935 */         if (resource != null)
/*      */         {
/*      */           try
/*      */           {
/* 2939 */             binaryStream = resource.streamContent();
/*      */           } catch (IOException e) {
/* 2941 */             return null;
/*      */           }
/*      */ 
/* 2944 */           if ((this.needConvert) && 
/* 2945 */             (path.endsWith(".properties"))) {
/* 2946 */             fileNeedConvert = true;
/*      */           }
/*      */ 
/* 2952 */           synchronized (this.allPermission)
/*      */           {
/* 2956 */             long[] result2 = new long[this.lastModifiedDates.length + 1];
/*      */ 
/* 2958 */             for (int j = 0; j < this.lastModifiedDates.length; j++) {
/* 2959 */               result2[j] = this.lastModifiedDates[j];
/*      */             }
/* 2961 */             result2[this.lastModifiedDates.length] = entry.lastModified;
/* 2962 */             this.lastModifiedDates = result2;
/*      */ 
/* 2964 */             String[] result = new String[this.paths.length + 1];
/* 2965 */             for (j = 0; j < this.paths.length; j++) {
/* 2966 */               result[j] = this.paths[j];
/*      */             }
/* 2968 */             result[this.paths.length] = fullPath;
/* 2969 */             this.paths = result;
/*      */           }
/*      */         }
/*      */ 
/*      */       }
/*      */       catch (NamingException e)
/*      */       {
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/* 2980 */     if ((entry == null) && (this.notFoundResources.containsKey(name))) {
/* 2981 */       return null;
/*      */     }
/* 2983 */     JarEntry jarEntry = null;
/*      */ 
/* 2985 */     synchronized (this.jarFiles)
/*      */     {
/*      */       try {
/* 2988 */         if (!openJARs()) {
/* 2989 */           attributes = null;
/*      */ 
/* 3136 */           if (binaryStream != null)
/*      */             try {
/* 3138 */               binaryStream.close(); } catch (IOException e) {
/*      */             }
/* 3139 */           return attributes;
/*      */         }
/*      */         JarEntry jarEntry2;
/* 2991 */         for (i = 0; (entry == null) && (i < jarFilesLength); i++)
/*      */         {
/* 2993 */           jarEntry = this.jarFiles[i].getJarEntry(path);
/*      */ 
/* 2995 */           if (jarEntry == null)
/*      */             continue;
/* 2997 */           entry = new ResourceEntry();
/*      */           try {
/* 2999 */             entry.codeBase = getURL(this.jarRealFiles[i], false);
/* 3000 */             String jarFakeUrl = getURI(this.jarRealFiles[i]).toString();
/* 3001 */             jarFakeUrl = new StringBuilder().append("jar:").append(jarFakeUrl).append("!/").append(path).toString();
/* 3002 */             entry.source = new URL(jarFakeUrl);
/* 3003 */             entry.lastModified = this.jarRealFiles[i].lastModified();
/*      */           } catch (MalformedURLException e) {
/* 3005 */             e = null;
/*      */ 
/* 3136 */             if (binaryStream != null)
/*      */               try {
/* 3138 */                 binaryStream.close(); } catch (IOException e) {
/*      */               }
/* 3139 */             return e;
/*      */           }
/* 3007 */           contentLength = (int)jarEntry.getSize();
/*      */           try {
/* 3009 */             entry.manifest = this.jarFiles[i].getManifest();
/* 3010 */             binaryStream = this.jarFiles[i].getInputStream(jarEntry);
/*      */           } catch (IOException e) {
/* 3012 */             e = null;
/*      */ 
/* 3136 */             if (binaryStream != null)
/*      */               try {
/* 3138 */                 binaryStream.close(); } catch (IOException e) {
/*      */               }
/* 3139 */             return e;
/*      */           }
/* 3016 */           if ((this.antiJARLocking) && (!path.endsWith(".class"))) {
/* 3017 */             byte[] buf = new byte[1024];
/* 3018 */             File resourceFile = new File(this.loaderDir, jarEntry.getName());
/*      */ 
/* 3020 */             if (!resourceFile.exists()) {
/* 3021 */               Enumeration entries = this.jarFiles[i].entries();
/*      */ 
/* 3023 */               while (entries.hasMoreElements()) {
/* 3024 */                 jarEntry2 = (JarEntry)entries.nextElement();
/* 3025 */                 if ((!jarEntry2.isDirectory()) && (!jarEntry2.getName().endsWith(".class")))
/*      */                 {
/* 3028 */                   resourceFile = new File(this.loaderDir, jarEntry2.getName());
/*      */                   try
/*      */                   {
/* 3031 */                     if (!resourceFile.getCanonicalPath().startsWith(this.canonicalLoaderDir))
/*      */                     {
/* 3033 */                       throw new IllegalArgumentException(sm.getString("webappClassLoader.illegalJarPath", new Object[] { jarEntry2.getName() }));
/*      */                     }
/*      */                   }
/*      */                   catch (IOException ioe)
/*      */                   {
/* 3038 */                     throw new IllegalArgumentException(sm.getString("webappClassLoader.validationErrorJarPath", new Object[] { jarEntry2.getName() }), ioe);
/*      */                   }
/*      */ 
/* 3042 */                   File parentFile = resourceFile.getParentFile();
/* 3043 */                   if ((!parentFile.mkdirs()) && (!parentFile.exists()));
/* 3046 */                   FileOutputStream os = null;
/* 3047 */                   InputStream is = null;
/*      */                   try {
/* 3049 */                     is = this.jarFiles[i].getInputStream(jarEntry2);
/*      */ 
/* 3051 */                     os = new FileOutputStream(resourceFile);
/*      */                     while (true)
/*      */                     {
/* 3054 */                       int n = is.read(buf);
/* 3055 */                       if (n <= 0) {
/*      */                         break;
/*      */                       }
/* 3058 */                       os.write(buf, 0, n);
/*      */                     }
/* 3060 */                     resourceFile.setLastModified(jarEntry2.getTime());
/*      */                   }
/*      */                   catch (IOException e) {
/*      */                   }
/*      */                   finally {
/*      */                     try {
/* 3066 */                       if (is != null)
/* 3067 */                         is.close();
/*      */                     }
/*      */                     catch (IOException e)
/*      */                     {
/*      */                     }
/*      */                     try {
/* 3073 */                       if (os != null) {
/* 3074 */                         os.close();
/*      */                       }
/*      */                     }
/*      */                     catch (IOException e)
/*      */                     {
/*      */                     }
/*      */                   }
/*      */                 }
/*      */               }
/*      */             }
/*      */ 
/*      */           }
/*      */ 
/*      */         }
/*      */ 
/* 3089 */         if (entry == null) {
/* 3090 */           synchronized (this.notFoundResources) {
/* 3091 */             this.notFoundResources.put(name, name);
/*      */           }
/* 3093 */           ??? = null;
/*      */ 
/* 3136 */           if (binaryStream != null)
/*      */             try {
/* 3138 */               binaryStream.close(); } catch (IOException e) {
/*      */             }
/* 3139 */           return ???;
/*      */         }
/* 3096 */         if (binaryStream != null)
/*      */         {
/* 3098 */           byte[] binaryContent = new byte[contentLength];
/*      */ 
/* 3100 */           int pos = 0;
/*      */           try
/*      */           {
/*      */             while (true) {
/* 3104 */               int n = binaryStream.read(binaryContent, pos, binaryContent.length - pos);
/*      */ 
/* 3106 */               if (n <= 0)
/*      */                 break;
/* 3108 */               pos += n;
/*      */             }
/*      */           } catch (IOException e) {
/* 3111 */             log.error(sm.getString("webappClassLoader.readError", new Object[] { name }), e);
/* 3112 */             jarEntry2 = null;
/*      */ 
/* 3136 */             if (binaryStream != null)
/*      */               try {
/* 3138 */                 binaryStream.close(); } catch (IOException e) {
/*      */               }
/* 3139 */             return jarEntry2;
/*      */           }
/* 3114 */           if (fileNeedConvert)
/*      */           {
/* 3119 */             String str = new String(binaryContent, 0, pos);
/*      */             try {
/* 3121 */               binaryContent = str.getBytes(CHARSET_UTF8);
/*      */             } catch (Exception e) {
/* 3123 */               e = null;
/*      */ 
/* 3136 */               if (binaryStream != null)
/*      */                 try {
/* 3138 */                   binaryStream.close(); } catch (IOException e) {
/*      */                 }
/* 3139 */               return e;
/*      */             }
/*      */           }
/* 3126 */           entry.binaryContent = binaryContent;
/*      */ 
/* 3130 */           if (jarEntry != null)
/* 3131 */             entry.certificates = jarEntry.getCertificates();
/*      */         }
/*      */       }
/*      */       finally
/*      */       {
/* 3136 */         if (binaryStream != null)
/*      */           try {
/* 3138 */             binaryStream.close();
/*      */           }
/*      */           catch (IOException e)
/*      */           {
/*      */           }
/*      */       }
/*      */     }
/* 3145 */     synchronized (this.resourceEntries)
/*      */     {
/* 3149 */       ResourceEntry entry2 = (ResourceEntry)this.resourceEntries.get(name);
/* 3150 */       if (entry2 == null)
/* 3151 */         this.resourceEntries.put(name, entry);
/*      */       else {
/* 3153 */         entry = entry2;
/*      */       }
/*      */     }
/*      */ 
/* 3157 */     return entry;
/*      */   }
/*      */ 
/*      */   protected boolean isPackageSealed(String name, Manifest man)
/*      */   {
/* 3168 */     String path = new StringBuilder().append(name.replace('.', '/')).append('/').toString();
/* 3169 */     Attributes attr = man.getAttributes(path);
/* 3170 */     String sealed = null;
/* 3171 */     if (attr != null) {
/* 3172 */       sealed = attr.getValue(Attributes.Name.SEALED);
/*      */     }
/* 3174 */     if ((sealed == null) && 
/* 3175 */       ((attr = man.getMainAttributes()) != null)) {
/* 3176 */       sealed = attr.getValue(Attributes.Name.SEALED);
/*      */     }
/*      */ 
/* 3179 */     return "true".equalsIgnoreCase(sealed);
/*      */   }
/*      */ 
/*      */   protected InputStream findLoadedResource(String name)
/*      */   {
/* 3194 */     ResourceEntry entry = (ResourceEntry)this.resourceEntries.get(name);
/* 3195 */     if ((entry != null) && 
/* 3196 */       (entry.binaryContent != null)) {
/* 3197 */       return new ByteArrayInputStream(entry.binaryContent);
/*      */     }
/* 3199 */     return null;
/*      */   }
/*      */ 
/*      */   protected Class<?> findLoadedClass0(String name)
/*      */   {
/* 3213 */     ResourceEntry entry = (ResourceEntry)this.resourceEntries.get(name);
/* 3214 */     if (entry != null) {
/* 3215 */       return entry.loadedClass;
/*      */     }
/* 3217 */     return null;
/*      */   }
/*      */ 
/*      */   protected void refreshPolicy()
/*      */   {
/*      */     try
/*      */     {
/* 3231 */       Policy policy = Policy.getPolicy();
/* 3232 */       policy.refresh();
/*      */     }
/*      */     catch (AccessControlException e)
/*      */     {
/*      */     }
/*      */   }
/*      */ 
/*      */   protected boolean filter(String name)
/*      */   {
/* 3249 */     if (name == null) {
/* 3250 */       return false;
/*      */     }
/*      */ 
/* 3253 */     String packageName = null;
/* 3254 */     int pos = name.lastIndexOf(46);
/* 3255 */     if (pos != -1)
/* 3256 */       packageName = name.substring(0, pos);
/*      */     else {
/* 3258 */       return false;
/*      */     }
/* 3260 */     for (int i = 0; i < packageTriggers.length; i++) {
/* 3261 */       if (packageName.startsWith(packageTriggers[i])) {
/* 3262 */         return true;
/*      */       }
/*      */     }
/* 3265 */     return false;
/*      */   }
/*      */ 
/*      */   protected boolean validate(String name)
/*      */   {
/* 3283 */     if (name == null)
/*      */     {
/* 3285 */       return false;
/*      */     }
/* 3287 */     if (name.startsWith("java."))
/*      */     {
/* 3289 */       return false;
/*      */     }
/* 3291 */     if (name.startsWith("javax.servlet.jsp.jstl"))
/*      */     {
/* 3293 */       return true;
/*      */     }
/* 3295 */     if (name.startsWith("javax.servlet."))
/*      */     {
/* 3297 */       return false;
/*      */     }
/*      */ 
/* 3301 */     return !name.startsWith("javax.el");
/*      */   }
/*      */ 
/*      */   protected boolean validateJarFile(File jarfile)
/*      */     throws IOException
/*      */   {
/* 3321 */     if (triggers == null)
/* 3322 */       return true;
/* 3323 */     JarFile jarFile = new JarFile(jarfile);
/* 3324 */     for (int i = 0; i < triggers.length; i++) {
/* 3325 */       Class clazz = null;
/*      */       try {
/* 3327 */         if (this.parent != null)
/* 3328 */           clazz = this.parent.loadClass(triggers[i]);
/*      */         else
/* 3330 */           clazz = Class.forName(triggers[i]);
/*      */       }
/*      */       catch (Exception e) {
/* 3333 */         clazz = null;
/*      */       }
/* 3335 */       if (clazz == null)
/*      */         continue;
/* 3337 */       String name = new StringBuilder().append(triggers[i].replace('.', '/')).append(".class").toString();
/* 3338 */       if (log.isDebugEnabled())
/* 3339 */         log.debug(new StringBuilder().append(" Checking for ").append(name).toString());
/* 3340 */       JarEntry jarEntry = jarFile.getJarEntry(name);
/* 3341 */       if (jarEntry != null) {
/* 3342 */         log.info(new StringBuilder().append("validateJarFile(").append(jarfile).append(") - jar not loaded. See Servlet Spec 2.3, ").append("section 9.7.2. Offending class: ").append(name).toString());
/*      */ 
/* 3345 */         jarFile.close();
/* 3346 */         return false;
/*      */       }
/*      */     }
/* 3349 */     jarFile.close();
/* 3350 */     return true;
/*      */   }
/*      */ 
/*      */   protected URL getURL(File file, boolean encoded)
/*      */     throws MalformedURLException
/*      */   {
/* 3361 */     File realFile = file;
/*      */     try {
/* 3363 */       realFile = realFile.getCanonicalFile();
/*      */     }
/*      */     catch (IOException e) {
/*      */     }
/* 3367 */     if (encoded) {
/* 3368 */       return getURI(realFile);
/*      */     }
/*      */ 
/* 3371 */     return realFile.toURI().toURL();
/*      */   }
/*      */ 
/*      */   protected URL getURI(File file)
/*      */     throws MalformedURLException
/*      */   {
/* 3382 */     File realFile = file;
/*      */     try {
/* 3384 */       realFile = realFile.getCanonicalFile();
/*      */     }
/*      */     catch (IOException e) {
/*      */     }
/* 3388 */     return realFile.toURI().toURL();
/*      */   }
/*      */ 
/*      */   protected static void deleteDir(File dir)
/*      */   {
/* 3401 */     String[] files = dir.list();
/* 3402 */     if (files == null) {
/* 3403 */       files = new String[0];
/*      */     }
/* 3405 */     for (int i = 0; i < files.length; i++) {
/* 3406 */       File file = new File(dir, files[i]);
/* 3407 */       if (file.isDirectory())
/* 3408 */         deleteDir(file);
/*      */       else {
/* 3410 */         file.delete();
/*      */       }
/*      */     }
/* 3413 */     dir.delete();
/*      */   }
/*      */ 
/*      */   static
/*      */   {
/*  141 */     JVM_THREAD_GROUP_NAMES.add("system");
/*  142 */     JVM_THREAD_GROUP_NAMES.add("RMI Runtime");
/*      */ 
/*  191 */     triggers = new String[] { "javax.servlet.Servlet", "javax.el.Expression" };
/*      */ 
/*  200 */     packageTriggers = new String[0];
/*      */ 
/*  207 */     sm = StringManager.getManager("org.apache.catalina.loader");
/*      */   }
/*      */ 
/*      */   protected static final class PrivilegedGetClassLoader
/*      */     implements PrivilegedAction<ClassLoader>
/*      */   {
/*      */     public Class<?> clazz;
/*      */ 
/*      */     public PrivilegedGetClassLoader(Class<?> clazz)
/*      */     {
/*  170 */       this.clazz = clazz;
/*      */     }
/*      */ 
/*      */     public ClassLoader run()
/*      */     {
/*  175 */       return this.clazz.getClassLoader();
/*      */     }
/*      */   }
/*      */ 
/*      */   protected class PrivilegedFindResourceByName
/*      */     implements PrivilegedAction<ResourceEntry>
/*      */   {
/*      */     protected String name;
/*      */     protected String path;
/*      */ 
/*      */     PrivilegedFindResourceByName(String name, String path)
/*      */     {
/*  152 */       this.name = name;
/*  153 */       this.path = path;
/*      */     }
/*      */ 
/*      */     public ResourceEntry run()
/*      */     {
/*  158 */       return WebappClassLoader.this.findResourceInternal(this.name, this.path);
/*      */     }
/*      */   }
/*      */ }

/* Location:           /usr/share/apache-tomcat-7.0.25/lib/catalina.jar
 * Qualified Name:     org.apache.catalina.loader.WebappClassLoader
 * JD-Core Version:    0.6.0
 */