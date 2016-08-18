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
        <asp:LinkButton ID="社会新闻" runat="server" OnClick="社会新闻_Click">社会新闻</asp:LinkButton>
        &nbsp;&nbsp;&nbsp;
        <asp:LinkButton ID="时政新闻" runat="server" OnClick="时政新闻_Click">时政新闻</asp:LinkButton>
        &nbsp;&nbsp;&nbsp;
 <%--       <asp:LinkButton ID="IT" runat="server" OnClick="IT_Click">IT技术</asp:LinkButton>
        &nbsp;&nbsp;&nbsp;
        <asp:LinkButton ID="Markdown" runat="server" OnClick="Markdown_Click">Markdown</asp:LinkButton>
        &nbsp;&nbsp;&nbsp;
        <asp:LinkButton ID="java" runat="server" OnClick="java_Click">java</asp:LinkButton>
        &nbsp;&nbsp;&nbsp;--%>
        <asp:LinkButton ID="quan" runat="server" OnClick="quan_Click">全部</asp:LinkButton>  
    </div>

    <article class="index" style="margin-top:2px;">
        <div class="type_wrapper">
            <asp:GridView ID="GridView1" CssClass="gridview" runat="server" AutoGenerateColumns="False" DataKeyNames="fileid" GridLines="None" ShowHeader="False" AllowPaging="True" AllowSorting="True" DataSourceID="SqlDataSource1">
                <Columns>
                    <asp:TemplateField SortExpression="filename" HeaderText="新闻标题">
                        <ItemTemplate>
                            <%--<asp:HyperLink CssClass="item" ID="HyperLink" runat="server" Text='<%# "<h2>["+Eval("type", "{0}")+"]  "+Eval("filename", "{0}")+"</h2><br/><h5>发表于 "+Eval("publishdate", "{0:d}")+"</h5><br/><p>"+Eval("filecontent","{0}").ToString().Substring(0,30)+"...</p><br/><hr/>" %>' BorderStyle="None" NavigateUrl='<%# "content.aspx?id="+Eval("fileid") %>'></asp:HyperLink>--%>
                            <asp:HyperLink CssClass="item" ID="HyperLink" runat="server" Text='<%# "<h2>["+Eval("type", "{0}")+"]  "+Eval("filename", "{0}")+"</h2><br/><h5>发表于 "+Eval("publishdate", "{0:d}")+"</h5>" %>' BorderStyle="None" NavigateUrl='<%# "content.aspx?id="+Eval("fileid") %>'></asp:HyperLink>
                            <br />
                        </ItemTemplate>
                    </asp:TemplateField>
                </Columns>
                <HeaderStyle BorderStyle="None" />
            </asp:GridView>

            <asp:SqlDataSource ID="SqlDataSource1" runat="server" ConnectionString="<%$ ConnectionStrings:newsConnectionString %>" SelectCommand="SELECT * FROM files"  ProviderName="<%$ ConnectionStrings:newsConnectionString.ProviderName %>" ></asp:SqlDataSource>
        </div>
    </article>
   
</asp:Content>
