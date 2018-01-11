/* 
	Description:
		ZK Essentials
	History:
		Created by dennis

Copyright (C) 2012 Potix Corporation. All Rights Reserved.
*/
package org.zkoss.essentials.chapter2;

import org.zkoss.bind.annotation.*;
import org.zkoss.essentials.services.*;
import org.zkoss.zk.ui.Executions;

import java.util.List;

public class SidebarViewModel {

    private SidebarPageConfig pageConfig = new SidebarPageConfigChapter2Impl();

    public List<SidebarPage> getSidebarPages() {
        return pageConfig.getPages();
    }

    @Command
    public void navigate(@BindingParam("page") SidebarPage page) {
        Executions.getCurrent().sendRedirect(page.getUri());
    }
}
