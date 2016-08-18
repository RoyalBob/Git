<%@ Page Title="" Language="C#" MasterPageFile="~/MasterPage.master" AutoEventWireup="true" CodeFile="index.aspx.cs"  Inherits="_Default" %>

<asp:Content ID="Content1" ContentPlaceHolderID="ContentPlaceHolder1" Runat="Server">
    <article class="index">
        <div class="wrapper">
            <asp:GridView ID="GridView1" CssClass="gridview" runat="server" AutoGenerateColumns="False" DataKeyNames="fileid" GridLines="None" ShowHeader="False" AllowPaging="True" AllowSorting="True" DataSourceID="SqlDataSource1" PageSize="8" Font-Size="Medium">
                <Columns>
                    <asp:TemplateField SortExpression="publishdate" HeaderText="新闻标题">
                        <ItemTemplate>
                            <asp:HyperLink CssClass="item" ID="HyperLink" runat="server" Text='<%# "<h2>["+Eval("typename", "{0}")+"]  "+Eval("filename", "{0}")+"</h2><br/><h5>发表于 "+Eval("publishdate", "{0:d}")+"</h5>" %>' BorderStyle="None" NavigateUrl='<%# "content.aspx?id="+Eval("fileid") %>'></asp:HyperLink>
                            <%--<asp:HyperLink CssClass="item" ID="HyperLink1" runat="server" Text='<%# "<h2>["+Eval("type", "{0}")+"]  "+Eval("filename", "{0}")+"</h2><br/><h5>发表于 "+Eval("publishdate", "{0:d}")+"</h5><br/><p>"+Eval("filecontent","{0}").ToString().Substring(0,50)+"...</p><br/><hr/>" %>' BorderStyle="None" NavigateUrl='<%# "content.aspx?id="+Eval("fileid") %>'></asp:HyperLink>--%>
                            <br />
                        </ItemTemplate>
                    </asp:TemplateField>
                </Columns>
                <HeaderStyle BorderStyle="None" />
                <PagerSettings PageButtonCount="25" />
            </asp:GridView>

            <asp:SqlDataSource ID="SqlDataSource1" runat="server" ConnectionString="<%$ ConnectionStrings:newsConnectionString %>" SelectCommand="SELECT * FROM files WHERE publishdate=CURDATE() or publishdate=CURDATE()-1 ORDER BY typename='奥运体育' desc,publishdate DESC,fileid DESC" ProviderName="<%$ ConnectionStrings:newsConnectionString.ProviderName %>" />
        </div>
    </article>
    <script type="text/javascript">
        $(function () {
            //$("textarea").html($("#editormd_content").html());
            //$("footer").remove();
            //$("canvas").remove();
        });
    </script>
</asp:Content>
