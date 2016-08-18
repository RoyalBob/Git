<%@ Page Title="" Language="C#" MasterPageFile="~/MasterPage.master" AutoEventWireup="true" CodeFile="type.aspx.cs" Inherits="type" %>

<asp:Content ID="Content1" ContentPlaceHolderID="ContentPlaceHolder1" Runat="Server">
    <div class="type_top1">
        <span>分类类型：</span>
        <asp:Label ID="type1" runat="server"></asp:Label>
        <asp:LinkButton ID="type1_b" runat="server" CssClass="type_img" OnClick="type1_b_Click">×</asp:LinkButton>
        <asp:Label ID="type2" runat="server"></asp:Label>
        <asp:LinkButton ID="type2_b" runat="server" CssClass="type_img" OnClick="type2_b_Click">×</asp:LinkButton>
    </div>

    <div class="type_top2">
        <span>分类类别：</span>
        <asp:LinkButton ID="奥运" runat="server" OnClick="奥运_Click">奥运</asp:LinkButton>
        &nbsp;&nbsp;&nbsp;
        <asp:LinkButton ID="国际" runat="server" OnClick="国际_Click">国际</asp:LinkButton>
        &nbsp;&nbsp;&nbsp;
        <asp:LinkButton ID="社会" runat="server" OnClick="社会_Click">社会</asp:LinkButton>
        &nbsp;&nbsp;&nbsp;
        <asp:LinkButton ID="时政" runat="server" OnClick="时政_Click">时政</asp:LinkButton>
        &nbsp;&nbsp;&nbsp;
        <asp:LinkButton ID="各地" runat="server" OnClick="各地_Click">各地</asp:LinkButton>
        &nbsp;&nbsp;&nbsp;
        <asp:LinkButton ID="港澳台" runat="server" OnClick="港澳台_Click">港澳台</asp:LinkButton>
        &nbsp;&nbsp;&nbsp;
        <asp:LinkButton ID="经济" runat="server" OnClick="经济_Click">经济</asp:LinkButton>
        &nbsp;&nbsp;&nbsp;
        <asp:LinkButton ID="军事" runat="server" OnClick="军事_Click">军事</asp:LinkButton>
        &nbsp;&nbsp;&nbsp;
        <asp:LinkButton ID="quan" runat="server" OnClick="quan_Click">全部</asp:LinkButton>  
    </div>
    <article class="index" style="margin-top:2px;">
        <div class="type_wrapper">
            <asp:GridView ID="GridView1" CssClass="gridview" runat="server" AutoGenerateColumns="False" DataKeyNames="fileid" GridLines="None" ShowHeader="False" AllowPaging="True" AllowSorting="True" DataSourceID="SqlDataSource1" Font-Size="Medium">
                <Columns>
                    <asp:TemplateField SortExpression="publishdate" HeaderText="新闻标题">
                        <ItemTemplate>
                            <asp:HyperLink CssClass="item" ID="HyperLink" runat="server" Text='<%# "<h2>["+Eval("typename", "{0}")+"]  "+Eval("filename", "{0}")+"</h2><br/><h5>发表于 "+Eval("publishdate", "{0:d}")+"</h5>" %>' BorderStyle="None" NavigateUrl='<%# "content.aspx?id="+Eval("fileid") %>'></asp:HyperLink>
                            <br />
                        </ItemTemplate>
                    </asp:TemplateField>
                </Columns>
                <HeaderStyle BorderStyle="None" />
<%--                <PagerTemplate>  
                    <br />  
                    <asp:Label ID="lblPage" runat="server" Text='<%# "第" + (((GridView)Container.NamingContainer).PageIndex + 1)  + "页/共" + (((GridView)Container.NamingContainer).PageCount) + "页" %> '></asp:Label>  
                    <asp:LinkButton ID="lbnFirst" runat="Server" Text="首页"  Enabled='<%# ((GridView)Container.NamingContainer).PageIndex != 0 %>' CommandName="Page" CommandArgument="First" ></asp:LinkButton>  
                    <asp:LinkButton ID="lbnPrev" runat="server" Text="上一页" Enabled='<%# ((GridView)Container.NamingContainer).PageIndex != 0 %>' CommandName="Page" CommandArgument="Prev"  ></asp:LinkButton>  
                    <asp:LinkButton ID="lbnNext" runat="Server" Text="下一页" Enabled='<%# ((GridView)Container.NamingContainer).PageIndex != (((GridView)Container.NamingContainer).PageCount - 1) %>' CommandName="Page" CommandArgument="Next" ></asp:LinkButton>  
                    <asp:LinkButton ID="lbnLast" runat="Server" Text="尾页"   Enabled='<%# ((GridView)Container.NamingContainer).PageIndex != (((GridView)Container.NamingContainer).PageCount - 1) %>' CommandName="Page" CommandArgument="Last" ></asp:LinkButton>  
                </PagerTemplate>  --%>
                <PagerSettings PageButtonCount="25" />
            </asp:GridView>

            <asp:SqlDataSource ID="SqlDataSource1" runat="server" ConnectionString="<%$ ConnectionStrings:newsConnectionString %>" SelectCommand="SELECT * FROM files group by publishidate ORDER BY publishdate DESC"  ProviderName="<%$ ConnectionStrings:newsConnectionString.ProviderName %>" />
        </div>
    </article>
   
</asp:Content>
