<%@ Page Title="" Language="C#" MasterPageFile="~/MasterPage.master" AutoEventWireup="true" CodeFile="content.aspx.cs" Inherits="content" %>

<asp:Content ID="Content1" ContentPlaceHolderID="ContentPlaceHolder1" Runat="Server">
    <article class="content" runat="server">
            <div class="wrapper" Runat="Server">
                <asp:GridView CssClass="gridview" ID="GridView1" runat="server" DataSourceID="SqlDataSource1" GridLines="None" ShowHeader="False" AutoGenerateColumns="False">
                    <Columns>
                        <asp:TemplateField>
                            <ItemTemplate>
                                <asp:Label ID="Label1" runat="server" Text='<%# "<h1>"+Eval("filename")+"</h1>" %>'></asp:Label>
                                <br />
                                <asp:Label  ID="Label2" runat="server" Text='<%# "<h5>发表于："+Eval("publishdate", "{0:D}") +"</h5>" %>'></asp:Label>
                                <br />
                                <%--<asp:Label CssClass="content" ID="Label3" runat="server" Text='<%# "<div id=\"article_content2\">" +Eval("filecontent")+"</div><div class=\"docstyle\" id=\"article_content\"></div>" %>'></asp:Label>--%>
                                <asp:Label CssClass="content" ID="Label3" runat="server" Text='<%# "<div class=\"docstyle\" id=\"article_content\">" + Eval("filecontent") + "</div>" %>'></asp:Label>
                            </ItemTemplate>
                        </asp:TemplateField>
                    </Columns>
                </asp:GridView>

                <asp:SqlDataSource ID="SqlDataSource1" runat="server" ConnectionString="<%$ ConnectionStrings:newsConnectionString %>" SelectCommand="SELECT filename, filecontent, publishdate FROM files WHERE fileid = @fileid" ProviderName="<%$ ConnectionStrings:newsConnectionString.ProviderName %>">
                    <SelectParameters>
                        <asp:QueryStringParameter Name="fileid" QueryStringField="id" Type="Int32" />
                    </SelectParameters>
                </asp:SqlDataSource>

                <asp:SqlDataSource ID="SqlDataSource2" runat="server" ConnectionString="<%$ ConnectionStrings:newsConnectionString %>" SelectCommand="SELECT * FROM pinglun WHERE fileid = @fileid" ProviderName="<%$ ConnectionStrings:newsConnectionString.ProviderName %>">
                    <SelectParameters>
                        <asp:QueryStringParameter Name="fileid" QueryStringField="id" Type="Int32" />
                    </SelectParameters>
                </asp:SqlDataSource>

                <asp:SqlDataSource ID="SqlDataSource3" runat="server" ConnectionString="<%$ ConnectionStrings:newsConnectionString %>" DeleteCommand="DELETE FROM pinglun WHERE id = @original_id" OldValuesParameterFormatString="original_{0}" SelectCommand="SELECT * FROM pinglun WHERE (fileid = @fileid)" ProviderName="<%$ ConnectionStrings:newsConnectionString.ProviderName %>">
                    <DeleteParameters>
                        <asp:SessionParameter Name="original_id" SessionField="original_id" Type="Int32" />
                    </DeleteParameters>
                    <SelectParameters>
                        <asp:QueryStringParameter Name="fileid" QueryStringField="id" Type="Int32" />
                    </SelectParameters>
                </asp:SqlDataSource>

                <asp:Label ID="Label4" runat="server" Text="评论区" Font-Bold="True" Font-Overline="False" Font-Size="X-Large" Font-Underline="False"></asp:Label>
                <asp:GridView ID="DiscussGridView" CssClass="discuss" runat="server" AutoGenerateColumns="False" DataKeyNames="id" DataSourceID="SqlDataSource2" GridLines="None" AllowPaging="True" style="text-align: center">
                    <Columns>
                        <asp:BoundField DataField="username" HeaderText="用户" SortExpression="username">
                        <ItemStyle Width="250px" />
                        </asp:BoundField>
                        <asp:BoundField DataField="comment" HeaderText="评论" SortExpression="comment">
                        <ItemStyle Width="300px" />
                        </asp:BoundField>
                        <asp:BoundField DataField="publishdate" HeaderText="发表日期" SortExpression="publishdate" />                            
                    </Columns>
                </asp:GridView>

                <asp:GridView ID="GridView2" runat="server" AllowPaging="True" AutoGenerateColumns="False" DataKeyNames="id" DataSourceID="SqlDataSource3" GridLines="None" HorizontalAlign="Center" Visible="False" Width="100%" style="text-align: center">
                    <Columns>
                        <asp:BoundField DataField="comment" HeaderText="评论" SortExpression="comment" />
                        <asp:BoundField DataField="publishdate" HeaderText="发表日期" SortExpression="publishdate" />
                        <asp:BoundField DataField="username" HeaderText="用户名" SortExpression="username" />
                        <asp:CommandField ShowDeleteButton="True" />
                    </Columns>
                </asp:GridView>

                <%--<div class="input-wrapper">
                    <asp:TextBox ID="TxtSpeak" CssClass="content-speak" Runat="Server" Rows="2" Columns="2"  ></asp:TextBox>
                    <div class="discuss-lock" Runat="Server" id="discuss"><p>未登陆无法发表评论，或者<a class="discuss-unlock">以游客的身份</a>发表评论</p></div>
                    <asp:Button  ID="submit" CssClass="button content-speak-submit"  runat="server" Text="提交" OnClick="submit_Click" />
                </div>--%>
            </div>
         
    </article>

    <article class="index" runat="server" style="margin:0">
        <div class="wrapper" Runat="Server">
            <asp:GridView ID="GridView3" CssClass="gridview" runat="server" AutoGenerateColumns="False" DataKeyNames="fileid" GridLines="None" ShowHeader="False" AllowPaging="True" AllowSorting="True" DataSourceID="SqlDataSource4" PageSize="3" Font-Size="Medium">
                    <Columns>
                        <asp:TemplateField SortExpression="publishdate" HeaderText="新闻标题">
                            <ItemTemplate>
                                <asp:HyperLink CssClass="item" ID="HyperLink" runat="server" Text='<%# "<h2>["+Eval("typename", "{0}")+"]  "+Eval("filename", "{0}")+"</h2><h5>发表于 "+Eval("publishdate", "{0:d}")+"</h5>" %>' BorderStyle="None" NavigateUrl='<%# "content.aspx?id="+Eval("fileid") %>'></asp:HyperLink>
                                <br />
                            </ItemTemplate>
                        </asp:TemplateField>
                    
                    </Columns>
                    <HeaderStyle BorderStyle="None" />
                    <PagerSettings PageButtonCount="25" />
                </asp:GridView>

                <asp:SqlDataSource ID="SqlDataSource4" runat="server" ConnectionString="<%$ ConnectionStrings:newsConnectionString %>" SelectCommand="SELECT * FROM files where publishdate=CURDATE()" ProviderName="<%$ ConnectionStrings:newsConnectionString.ProviderName %>" />
            </div>
    </article>
    
    <%--<script>
        var text = document.getElementById('article_content2').innerText;
        document.getElementById('article_content').innerHTML = marked(text);
    </script>--%>
</asp:Content>

