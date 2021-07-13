<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Cryptx</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link href="https://fonts.googleapis.com/css?family=Ubuntu:500,600,700&display=swap" rel="stylesheet">

    <style>


        .compile-bill{
            font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
            box-sizing: border-box;
            font-size: 14px;
            width: 50%;
            display: block;
            margin: 0px auto;
            padding: 20px;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .compile-bill > table{
            font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
            box-sizing: border-box;
            font-size: 14px;
            border-radius: 7px;
            border: solid;
            margin: 0px;
            height: 50%;
            width: 100%;
        }

        .compile-bill>table>tr{
            font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
            box-sizing: border-box;
            font-size: 14px;
            margin: 0px;
        }

        .compile-bill>table>tr>td{
            font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
            box-sizing: border-box;
            text-align: center;
            font-size: 18px;
        }

        .compile-inner{
            border: solid;
            box-sizing: border-box;
            margin: 0;
            height: 10px;
        }

        .compile-inner>td{

            font-size: 12px;
            color: #9b9fa2;
            border-top: 1px solid rgb(238, 238, 238);
        }
        .inner-table{
            width: 100%;
            padding: 12px;
        }

        .inner-table-header{
            text-align: center;
            font-size: 18px;
        }

        .inner-footer>td{
            text-align: center;
            font-size: 9px;
        }

    </style>
</head>

<body>
<div class="compile-bill">
    
    <table>
        <tbody>
        <tr>
            <td class="inner-table-header">
                ${mainText}
            </td>
        </tr>
        <tr>
            <td>
                <table class="inner-table">
                    <tbody>
                        <#list data as ls>
                            <tr class="compile-inner">
                                <td>
                                    ${ls.key}
                                </td>
                                <td style="text-align: end">
                                    ${ls.value}
                                </td>
                            </tr>
                        </#list>
                    </tbody>
                </table>
            </td>
        </tr>
        <tr class="inner-footer">
            <td>
                Skote Inc. 2896 Howell Rd, Russellville, AR, 72823
            </td>
        </tr>
        <tr class="inner-footer">
            <td>
                @2021 tkats18
            </td>
        </tr>
        </tbody>
    </table>

</div>
</body>
</html>