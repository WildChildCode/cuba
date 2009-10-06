/*
 * Copyright (c) 2008 Haulmont Technology Ltd. All Rights Reserved.
 * Haulmont Technology proprietary and confidential.
 * Use is subject to license terms.

 * Author: Dmitry Abramov
 * Created: 29.12.2008 13:21:13
 * $Id$
 */
package com.haulmont.cuba.gui.components;

import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.gui.data.CollectionDatasource;
import org.dom4j.Element;

import java.util.List;

public interface Table
    extends
        com.haulmont.cuba.gui.components.List, Component.Editable, Component.HasSettings, Component.Expandable
{
    List<Column> getColumns();
    Column getColumn(String id);
    void addColumn(Column column);
    void removeColumn(Column column);

    void setDatasource(CollectionDatasource datasource);

    void setRequired(Column column, boolean required, String message);
    void addValidator(Column column, com.haulmont.cuba.gui.components.Field.Validator validator);

    void addValidator(com.haulmont.cuba.gui.components.Field.Validator validator);

    void setItemClickAction(Action action);
    Action getItemClickAction();

    void setSortable(boolean sortable);
    boolean isSortable();

    public class Column implements HasXmlDescriptor, HasCaption, HasFomatter {
        protected Object id;
        protected String caption;
        protected boolean editable;
        protected Formatter formatter;
        protected Integer width;
        protected boolean collapsed;

        protected Class type;
        private Element element;

        public Column(Object id) {
            this.id = id;
        }

        public Object getId() {
            return id;
        }

        public String getCaption() {
            return caption;
        }

        public void setCaption(String caption) {
            this.caption = caption;
        }

        public Boolean isEditable() {
            return editable;
        }

        public void setEditable(Boolean editable) {
            this.editable = editable;
        }

        public Class getType() {
            return type;
        }

        public void setType(Class type) {
            this.type = type;
        }

        public Formatter getFormatter() {
            return formatter;
        }

        public void setFormatter(Formatter formatter) {
            this.formatter = formatter;
        }

        public Integer getWidth() {
            return width;
        }

        public void setWidth(Integer width) {
            this.width = width;
        }

        public boolean isCollapsed() {
            return collapsed;
        }

        public void setCollapsed(boolean collapsed) {
            this.collapsed = collapsed;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Column column = (Column) o;

            return id.equals(column.id);

        }

        @Override
        public int hashCode() {
            return id.hashCode();
        }

        public Element getXmlDescriptor() {
            return element;
        }

        public void setXmlDescriptor(Element element) {
            this.element = element;
        }

        @Override
        public String toString() {
            return id == null ? super.toString() : id.toString();
        }
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    enum RowHeaderMode {
        NONE,
        ICON
    }

    void setRowHeaderMode(RowHeaderMode mode);

    interface StyleProvider {
        String getStyleName(Entity item, Object property);
        String getItemIcon(Entity item);
    }

    void setStyleProvider(StyleProvider styleProvider);

    enum PagingMode {
        PAGE,
        SCROLLING
    }

    void setPagingMode(PagingMode mode);

    interface PagingProvider {
        String firstCaption();
        String prevCaption();
        String nextCaption();
        String lastCaption();

        String pageLengthSelectorCaption();
        boolean showPageLengthSelector();
        int[] pageLengths();
    }

    void setPagingProvider(PagingProvider pagingProvider);

    interface ActionButtonsProvider {
        List<ActionButton> getButtons();
        ActionButton getButton(String id);
    }

    ActionButtonsProvider getActionButtonsProvider();
    void setActionButtonsProvider(ActionButtonsProvider buttonsPanel);

    public class ActionButton implements HasXmlDescriptor, HasCaption, HasIcon, ActionOwner {
        protected String id;
        protected String caption;
        protected String icon;
        protected Action action;
        protected Element element;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public Action getAction() {
            return action;
        }

        public void setAction(Action action) {
            this.action = action;
        }

        public Element getXmlDescriptor() {
            return element;
        }

        public void setXmlDescriptor(Element element) {
            this.element = element;
        }

        public String getCaption() {
            return caption;
        }

        public void setCaption(String caption) {
            this.caption = caption;
        }
    }
}
